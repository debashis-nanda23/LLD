package com.food.delivery.entity;

import java.util.HashMap;
import java.util.Map;

public class Menu {

	private final Map<String,MenuItem> items=new HashMap<>();
	
	public void addItem(MenuItem item) {
		this.items.put(item.getId(), item);
	}
	
	public MenuItem getItem(String id) {
		return this.items.get(id);
	}
	
	public Map<String,MenuItem> getItems(){
		return this.items;
	}
}
