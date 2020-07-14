package com.ufrgs.superfuturo.logic;

import java.util.HashMap;
import java.util.List;

import com.ufrgs.superfuturo.model.InputObjectPack;

import com.ufrgs.superfuturo.model.InputObject;
import com.ufrgs.superfuturo.model.InputPerson;


public class YoloParserLogic {
	
	public static void processNewInputObjects(final List<InputObject> oldInputObjects, final List<InputObject> newInputObjects) {
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		
		for (InputObject object : oldInputObjects) {
			
			final Integer quantity = map.get(object.getClasse());
			
			if (quantity == null) {
				map.put(object.getClasse(), 1);
			} else {
				map.put(object.getClasse(), quantity + 1);
			}
		}
		
		for (InputObject object : newInputObjects) {
			final Integer quantity = map.get(object.getClasse());
			
			if (quantity == null) {
				map.put(object.getClasse(), -1);
			} else {
				map.put(object.getClasse(), quantity - 1);
			}
		}
		
		for (String key : map.keySet()) {
			final Integer quantity = map.get(key);
			
			if (quantity > 0) {
				System.out.println("Foi retirado [" +  Math.abs(quantity) + "] unidades de [" + key + "]");
			}
			
			if (quantity < 0) {
				System.out.println("Foi adicionado  [" + Math.abs(quantity) + "] unidades de [" + key + "]");
			}
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
			}
			
			if (quantity < 0) {
				System.out.println("Foi adicionado  [" + Math.abs(quantity) + "] unidades de [" + key + "]");
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
}
