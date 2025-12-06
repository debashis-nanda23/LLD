package com.debashis.coffee.main;

import java.util.HashMap;
import java.util.Map;

import com.debashis.coffee.enums.Ingredient;

public class Inventory {
	
	private static final Inventory INSTANCE=new Inventory();
	private final Map<Ingredient,Integer> stock=new HashMap<>();
	
	private Inventory() {
		
	}
	
	public static Inventory getInstance() { 
		return INSTANCE;
	}
	
	public void addStock(Ingredient ingredient,int quantity) {
		this.stock.put(ingredient,stock.getOrDefault(ingredient, 0)+quantity);
	}
	
	public boolean hasIngredients(Map<Ingredient,Integer> recipes) {
		return recipes.entrySet().stream().allMatch(entry->stock.getOrDefault(entry.getKey(), 0)>=entry.getValue());
	}
	
	public synchronized void deductIngredient(Map<Ingredient,Integer> recipe) {
		if(!hasIngredients(recipe)) {
			System.out.println("Not enough ingredients to make coffee");
			return;
		}
		recipe.forEach((ingredient,quantity)->stock.put(ingredient,stock.get(ingredient)-quantity));
	}
	
	public void printInventory() {
		System.out.println("-----Current Inventory------");
		stock.forEach((ingredient,quantity)->System.out.println(ingredient+": "+quantity));
		System.out.println("===============================");
	}

}
