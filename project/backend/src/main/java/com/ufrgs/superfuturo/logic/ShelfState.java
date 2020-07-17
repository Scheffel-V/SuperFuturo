package com.ufrgs.superfuturo.logic;

import com.ufrgs.superfuturo.model.InputObject;
import org.hibernate.sql.ordering.antlr.OrderingSpecification;

/* @TODO
*  talk with team about idea:
*  what about if when loading we recorded each row and saved a state where we, by looking through the front
*  analyze and record what product was in that row, that way we can determine what exactly was the product that was removed
*  example:
*   A  I  V, then fill the next row  A  I  V
*                                    V  V  A
* so we know at position 1, 1 there was an I, even though we can't see it anymore, so we know that if
* the topmost row, middle column object disappears it was an 'I', unless some other change was detected there
*/

import java.util.*;
import java.util.stream.Collectors;

// This class assumes that each group is separated by belonging to a different x column
// (that is, it is the same group if it is found within DX_TOLERANCE)
// we also assume that the lower row is the smaller y coordinate (InputObject.by) and
// that leftmost product has smaller x coordinate (InputObject.bx)
// same goes for the front view parser, are assume that the leftmost product is the lower x value
public class ShelfState {
    // t1: 6 objs, positions...
    // t1: 3 obs, V, V, A
    // t2: 5 objs, positions...
    // t2: 3 obs, V, V, A
    // *                                    A  V  V
    //*                                     V  V  A
    //                                         *
    public static double DX_TOLERANCE = 0.007; // YOLO dimensions (normalized to 1)
    List<Group> groups = new ArrayList<>();

    public static class Group {
        final List<InputObject> members;
        final InputObject groupLeader;

        public Group(final List<InputObject> members, final InputObject groupLeader) {
            if (members.size() > 0) {
                members.sort(Comparator.comparingDouble(InputObject::getBy));
                this.members = members;
                this.groupLeader = groupLeader;
            } else {
                throw new IllegalStateException("Invalid group @ ShelfState. This should never happen");
            }
        }

        public static ShelfDeltaReport getDelta(final Group oldGroup, final Group newGroup) {
            if (newGroup.members.size() > oldGroup.members.size()) {
                // addition
                return new ShelfDeltaReport(newGroup.members.get(0), newGroup.groupLeader, 1);
            } else if (newGroup.members.size() < oldGroup.members.size()) {
                return new ShelfDeltaReport(oldGroup.members.get(0), oldGroup.groupLeader, -1);
            } else {
                return ShelfDeltaReport.emptyReport();
            }
        }

        public static Group emptyGroup() {
            return new Group();
        }

        private Group()  {
            this.members = new ArrayList<>();
            this.groupLeader = null;
        }

        public double getDx() {
            return members.get(0).getBx();
        }
    }

    public void addGroup(final List<InputObject> group, final InputObject groupLeader) {
        this.groups.add(new Group(group, groupLeader));
    }

    // both topParser and frontParser and failedFrontParser are expected to be sorted by the value of field bx
    // it's either this restriction, sychronizing the sorting of topParser and frontParser or
    // even creating a local copy of the list every time so we can sort locally
    public static ShelfState getShelfState(final List<InputObject> topParser, final List<InputObject> frontParser, final List<InputObject> failedFrontParser) {
        // @TODO assert are lists sorted?

        try {
            return ShelfState.getShelfState(topParser, frontParser);
        } catch (final IllegalStateException ex) {
            // the front update might've reached before top update,
            // at that time, we would've deemed this state inconsistent
            // however that might've been a valid case after top update is done
            // therefore, if we are inconsistent and we have a timeout we need to check it
            if (YoloParserLogic.inconsistentStateIsLatest() && YoloParserLogic.hasTimeoutBeenReached()) {
                final ShelfState shelfState = ShelfState.getShelfState(topParser, failedFrontParser);
                YoloParserLogic.undoInconsistentState();
                return shelfState;
            } else {
                throw ex;
            }
        }
    }

    private static ShelfState getShelfState(final List<InputObject> topParser, final List<InputObject> frontParser) {
        final ShelfState shelfState = new ShelfState();
        List<InputObject> temp = new ArrayList();
        double compDx = topParser.get(0).getBx();
        final Iterator<InputObject> frontParserIter = frontParser.iterator();

        for (final InputObject io : topParser) {
            final double diff = Math.abs(io.getBx() - compDx);

            if (diff >= DX_TOLERANCE) {
                // move into new group and commit last one
                validateFrontParserIterator(frontParserIter);
                shelfState.addGroup(temp, frontParserIter.next());
                temp = new ArrayList<>();
                compDx = io.getBx();
            }

            temp.add(io);
        }

        if (temp.size() > 0) {
            // if the last member also belonged to the same group,
            // it is still in temp, so we have to commit
            validateFrontParserIterator(frontParserIter);
            shelfState.addGroup(temp, frontParserIter.next());
        }

        validateFrontParserIterator(frontParserIter, true);

        return shelfState;
    }

    public static List<ShelfDeltaReport> getDelta(final ShelfState oldState, final ShelfState newState) {
        final List<Double> oldStateGroupsPositions = oldState.groups.stream().map(Group::getDx).collect(Collectors.toList());
        final List<Double> newStateGroupsPositions = newState.groups.stream().map(Group::getDx).collect(Collectors.toList());

        int oldIndex = 0;
        int newIndex = 0;

        final List<ShelfDeltaReport> deltaReports = new ArrayList<>();

        while (newIndex < newStateGroupsPositions.size() && oldIndex < oldStateGroupsPositions.size()) {
            double diff = newStateGroupsPositions.get(newIndex) - oldStateGroupsPositions.get(oldIndex);

            if (Math.abs(diff) < DX_TOLERANCE) {
                // same group
                deltaReports.add(Group.getDelta(oldState.groups.get(oldIndex), newState.groups.get(newIndex)));
                oldIndex++;
                newIndex++;
            } else if (diff > 0) {
                // new is too much to the right in relation to old, so we need to skip the current old as they
                // do not pair into the same group
                deltaReports.add(Group.getDelta(oldState.groups.get(oldIndex), Group.emptyGroup()));
                oldIndex++;
            } else {
                // old is too much to the right in relation to new, so we need to skip the current new as they
                // do not pair into the same group
                deltaReports.add(Group.getDelta(Group.emptyGroup(), newState.groups.get(oldIndex)));
                newIndex++;
            }
        }

        while (newIndex < newStateGroupsPositions.size()) {
            deltaReports.add(Group.getDelta(Group.emptyGroup(), newState.groups.get(oldIndex)));
            newIndex++;
        }

        while (oldIndex < oldStateGroupsPositions.size()) {
            deltaReports.add(Group.getDelta(oldState.groups.get(oldIndex), Group.emptyGroup()));
            oldIndex++;
        }

        return deltaReports;
    }

    private static void validateFrontParserIterator(final Iterator<InputObject> frontParserIter) {
        validateFrontParserIterator(frontParserIter, false);
    }

    private static void validateFrontParserIterator(final Iterator<InputObject> frontParserIter, final boolean expectEmpty) {
        final boolean validationPassed = expectEmpty ^ frontParserIter.hasNext();
        if (!validationPassed) {
            throw new IllegalStateException("Fatal mismatch between number of groups in top parser and number of groups in front parser");
        }
    }
}
