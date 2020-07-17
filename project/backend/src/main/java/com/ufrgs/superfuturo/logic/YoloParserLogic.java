package com.ufrgs.superfuturo.logic;

import java.sql.Date;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.ufrgs.superfuturo.model.InputObjectPack;
import com.ufrgs.superfuturo.model.InputObject;
import com.ufrgs.superfuturo.model.InputPerson;


public abstract class YoloParserLogic {

	private static final long TIMEOUT_TOLERANCE = 7000; // milliseconds
	private static List<InputObject> currentTopInputObjects = null;
	private static List<InputObject> currentFrontInputObjects = null;
	private static boolean isWaitingUpdate;
	private static ShelfState currentShelfState = null;

	public static void processFrontInputObjects(final List<InputObject> newInputObjects) {
		try { // only allow front update if it is consistent with top view
			ShelfState.getShelfState(YoloParserLogic.currentTopInputObjects, newInputObjects);
		} catch (final IllegalStateException ex) {
			// @log mismatch between top and front objects, inconsistent state, skipping until consistent again
			return;
		}

		YoloParserLogic.currentFrontInputObjects = newInputObjects;
	}

	public static void processTopInputObjects(final List<InputObject> newInputObjects) {
		// always allow top update
		YoloParserLogic.currentTopInputObjects = newInputObjects;
		YoloParserLogic.isWaitingUpdate = true;
	}

	public static void setupTopInputObjects(final List<InputObject> inputObjects) {
		YoloParserLogic.currentTopInputObjects = inputObjects;

		if (YoloParserLogic.isWaitingUpdate) {
			YoloParserLogic.currentShelfState = ShelfState.getShelfState(YoloParserLogic.currentTopInputObjects, YoloParserLogic.currentTopInputObjects);
			YoloParserLogic.isWaitingUpdate = false;
		} else {
			YoloParserLogic.isWaitingUpdate = true;
		}
	}

	public static void setupFrontInputObjects(final List<InputObject> inputObjects) {
		YoloParserLogic.currentFrontInputObjects = inputObjects;

		if (YoloParserLogic.isWaitingUpdate) {
			YoloParserLogic.currentShelfState = ShelfState.getShelfState(YoloParserLogic.currentTopInputObjects, YoloParserLogic.currentTopInputObjects);
			YoloParserLogic.isWaitingUpdate = false;
		} else {
			YoloParserLogic.isWaitingUpdate = true;
		}
	}

	public static void commitTransactions() {
		if (canUpdateShelfState()) {
			final ShelfState newShelfState = ShelfState.getShelfState(YoloParserLogic.currentTopInputObjects, YoloParserLogic.currentFrontInputObjects);
			final List<ShelfDeltaReport> listOfReports = ShelfState.getDelta(YoloParserLogic.currentShelfState, newShelfState);
			ShelfDeltaReport.commitAllTransactions(listOfReports);
			YoloParserLogic.currentShelfState = newShelfState;
		}
	}

	public static void processNewObjectPackList(final List<InputObjectPack> oldObjectPackList, final List<InputObjectPack> newObjectPackList) {
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		
		for (InputObjectPack object : oldObjectPackList) {
			final Integer quantity = map.get(object.getName());
			
			if (quantity == null) {
				map.put(object.getName(), object.getQuantity());
			} else {
				System.out.println("Two packs with same name. Error?");
			}
		}
		
		for (InputObjectPack object : newObjectPackList) {
			final Integer quantity = map.get(object.getName());
			
			if (quantity == null) {
				map.put(object.getName(), 0 - object.getQuantity());
			} else {
				map.put(object.getName(), quantity - object.getQuantity());
			}
		}
		
		for (String key : map.keySet()) {
			final Integer quantity = map.get(key);
			
			if (quantity > 0) {
				System.out.println("Foi retirado [" + Math.abs(quantity) + "] unidades de [" + key + "]");
				SuperFuturoLogic.userBuyProduct(key);
			}
			
			if (quantity < 0) {
				System.out.println("Foi adicionado  [" + Math.abs(quantity) + "] unidades de [" + key + "]");
				SuperFuturoLogic.userReturnProduct(key);
			}
		}
	}

	public static void processNewPersons(final List<InputPerson> oldPersons, final List<InputPerson> newPersons) {
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		
		for (InputPerson person : oldPersons) {
			final Integer quantity = map.get(person.getClasse());
			
			if (quantity == null) {
				map.put(person.getClasse(), 1);
			} else {
				map.put(person.getClasse(), quantity + 1);
			}
		}
		
		for (InputPerson person : newPersons) {
			final Integer quantity = map.get(person.getClasse());
			
			if (quantity == null) {
				map.put(person.getClasse(), -1);
			} else {
				map.put(person.getClasse(), quantity - 1);
			}
		}
		
		for (String key : map.keySet()) {
			final Integer quantity = map.get(key);
			
			if (quantity > 0) {
				System.out.println("A pessoa [" + key + "] sumiu");
			}
			
			if (quantity < 0) {
				System.out.println("Reconheci a pessoa [" + key + "]");
			}
		}
	}

	private static boolean canUpdateShelfState() {
		if(!YoloParserLogic.isWaitingUpdate)
			return false;

		YoloParserLogic.isWaitingUpdate = false;
		final boolean hasTimeoutExpired = Date.from(Instant.now()).getTime() - currentTopInputObjects.get(0).getTimestamp().getTime() > TIMEOUT_TOLERANCE;
		final boolean haveFrontInputObjectsBeenUpdated = currentFrontInputObjects.get(0).getTimestamp().getTime() > currentTopInputObjects.get(0).getTimestamp().getTime();

		return hasTimeoutExpired || haveFrontInputObjectsBeenUpdated;
	}
}
