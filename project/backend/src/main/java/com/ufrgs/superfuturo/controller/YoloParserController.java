package com.ufrgs.superfuturo.controller;

import java.util.HashMap;
import java.util.List;

import com.ufrgs.superfuturo.model.RealObject;

public class YoloParserController {
	
	public static void processNewObjectsList(final List<RealObject> oldObjectsList, final List<RealObject> newObjectsList) {
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		
		for (RealObject object : oldObjectsList) {
			final Integer quantity = map.get(object.getName());
			
			if (quantity == null) {
				map.put(object.getName(), 1);
			} else {
				map.put(object.getName(), quantity + 1);
			}
		}
		
		for (RealObject object : newObjectsList) {
			final Integer quantity = map.get(object.getName());
			
			if (quantity == null) {
				map.put(object.getName(), -1);
			} else {
				map.put(object.getName(), quantity - 1);
			}
		}
		
		System.out.println("################################");
		for (String key : map.keySet()) {
			final Integer quantity = map.get(key);
			
			if (quantity > 0) {
				System.out.println("Foi retirado [" + key + "]");
			}
			
			if (quantity < 0) {
				System.out.println("Foi adicionado [" + key + "]");
			}
		}
		System.out.println("################################");
	}
}
