package com.food.delivery.entity;

public class MenuItem {

	private final String id;
	private final String name;
	private final double price;
	private boolean available;
	
	public MenuItem(String id, String name, double price) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.available = true;
	}


	public void setAvailable(boolean available) {
		this.available = available;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public double getPrice() {
		return price;
	}
	
	
	public String getMenuItem() {
		return "Name,"+name+", Price "+price;
	}
	
	
}
