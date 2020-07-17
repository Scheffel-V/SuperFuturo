package com.ufrgs.superfuturo.logic;

import java.sql.Date;
import java.time.Instant;
import java.util.Comparator;
import java.util.List;

import com.ufrgs.superfuturo.model.InputObject;


public abstract class YoloParserLogic {

	public static final long TIMEOUT_TOLERANCE = 7000; // milliseconds
	private static List<InputObject> currentTopInputObjects = null;
	private static List<InputObject> currentFrontInputObjects = null;
	private static List<InputObject> inconsistentFrontInputObjects = null; // update was provided but it was deemed inconsistent at the time
	private static boolean isWaitingUpdate;
	private static ShelfState currentShelfState = null;
	private static boolean isInitialSetupDone = false;

	public static void processFrontInputObjects(final List<InputObject> newInputObjects) {

		newInputObjects.sort(Comparator.comparingDouble(InputObject::getBx));
		try { // only allow front update if it is consistent with top view
			ShelfState.getShelfState(YoloParserLogic.currentTopInputObjects, newInputObjects, YoloParserLogic.inconsistentFrontInputObjects);
		} catch (final IllegalStateException ex) {
			YoloParserLogic.inconsistentFrontInputObjects = newInputObjects;
			// @log mismatch between top and front objects, inconsistent state, skipping until consistent again
			return;
		}

		YoloParserLogic.currentFrontInputObjects = newInputObjects;
	}

	public static boolean isInputObjectListEmpty(final List<InputObject> inputObjectList) {
		return inputObjectList == null || inputObjectList.size() == 0;
	}

	public static boolean inconsistentStateIsLatest() {
		return !isInputObjectListEmpty(YoloParserLogic.inconsistentFrontInputObjects) && YoloParserLogic.inconsistentFrontInputObjects.get(0).getTimestamp().getTime() > YoloParserLogic.currentFrontInputObjects.get(0).getTimestamp().getTime();
	}

	public static void processTopInputObjects(final List<InputObject> newInputObjects) {
		// always allow top update
		newInputObjects.sort(Comparator.comparingDouble(InputObject::getBx));
		YoloParserLogic.currentTopInputObjects = newInputObjects;
		YoloParserLogic.isWaitingUpdate = true;
	}

	public static void setupTopInputObjects(final List<InputObject> inputObjects) {
		YoloParserLogic.currentTopInputObjects = inputObjects;

		if (YoloParserLogic.isWaitingUpdate) {
			YoloParserLogic.currentShelfState = ShelfState.getShelfState(YoloParserLogic.currentTopInputObjects, YoloParserLogic.currentFrontInputObjects, YoloParserLogic.inconsistentFrontInputObjects);
			YoloParserLogic.isWaitingUpdate = false;
			YoloParserLogic.isInitialSetupDone = true;
		} else {
			YoloParserLogic.isWaitingUpdate = true;
		}
	}

	public static void setupFrontInputObjects(final List<InputObject> inputObjects) {
		YoloParserLogic.currentFrontInputObjects = inputObjects;

		if (YoloParserLogic.isWaitingUpdate) {
			YoloParserLogic.currentShelfState = ShelfState.getShelfState(YoloParserLogic.currentTopInputObjects, YoloParserLogic.currentFrontInputObjects, YoloParserLogic.inconsistentFrontInputObjects);
			YoloParserLogic.isWaitingUpdate = false;
			YoloParserLogic.isInitialSetupDone = true;
		} else {
			YoloParserLogic.isWaitingUpdate = true;
		}
	}

	public static synchronized void commitTransactions() {
		if (canUpdateShelfState()) {
			final ShelfState newShelfState = ShelfState.getShelfState(YoloParserLogic.currentTopInputObjects, YoloParserLogic.currentFrontInputObjects, YoloParserLogic.inconsistentFrontInputObjects);
			final List<ShelfDeltaReport> listOfReports = ShelfState.getDelta(YoloParserLogic.currentShelfState, newShelfState);
			YoloParserLogic.currentShelfState = newShelfState;
			ShelfDeltaReport.commitAllTransactions(listOfReports);
		}
	}

	public static boolean isInitialSetupDone() {
		return YoloParserLogic.isInitialSetupDone;
	}

	private static boolean canUpdateShelfState() {
		if (!YoloParserLogic.isInitialSetupDone)
			return false;

		final boolean isEmpty = isInputObjectListEmpty(currentFrontInputObjects) || isInputObjectListEmpty(currentTopInputObjects);
		final boolean haveFrontInputObjectsBeenUpdated = !isEmpty && currentFrontInputObjects.get(0).getTimestamp().getTime() > currentTopInputObjects.get(0).getTimestamp().getTime();

		if (YoloParserLogic.isWaitingUpdate && (YoloParserLogic.hasTimeoutBeenReached() || haveFrontInputObjectsBeenUpdated)) {
			YoloParserLogic.isWaitingUpdate = false;
			return true;
		}

		return false;
	}

	public static boolean hasTimeoutBeenReached() {
		final boolean isEmpty = isInputObjectListEmpty(currentTopInputObjects);
		return !isEmpty && Date.from(Instant.now()).getTime() - currentTopInputObjects.get(0).getTimestamp().getTime() > TIMEOUT_TOLERANCE;
	}

	public static void undoInconsistentState() {
		// not inconsistent anymore (top was updated, so this is actually consistent now)
		YoloParserLogic.currentFrontInputObjects = YoloParserLogic.inconsistentFrontInputObjects;
		YoloParserLogic.inconsistentFrontInputObjects = null;
	}
}
