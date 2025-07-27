package com.coffee;

import java.util.HashMap;
import java.util.Map;

public class IngredientStore {
	
	private final Map<String,Integer> inventory=new HashMap<>();
	
	public synchronized void refill(String ingredient,int quantity) {
		inventory.put(ingredient,inventory.getOrDefault(ingredient, 0)+quantity);
	}
	
	public boolean hasEnoughIngreident(Map<String,Integer> required) {
		for(Map.Entry<String,Integer> map:required.entrySet()) {
			String ingredient=map.getKey();
			int available=inventory.getOrDefault(ingredient, 0);
			if(available<map.getValue()) {
				return false;
			}
		}
		return true;
	}
	
	public synchronized void consume(Map<String,Integer> required) {
		for(Map.Entry<String,Integer> map:required.entrySet()) {
			String ingredient=map.getKey();
			inventory.put(ingredient, inventory.get(ingredient)-map.getValue());
		}
	}
	
	public synchronized Map<String,Integer> getAllIngredients(){
		return new HashMap<String, Integer>(inventory);
	}
	
	public synchronized int getLevel(String ingredient) {
		return inventory.getOrDefault(ingredient, 0);
	}

}
