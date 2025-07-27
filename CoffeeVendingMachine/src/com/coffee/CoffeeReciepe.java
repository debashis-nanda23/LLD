package com.coffee;

import java.util.Map;

public class CoffeeReciepe {
	
	private final String name;
	private final double price;
	private final Map<String,Integer> reciepe;
	
	public CoffeeReciepe(String name, double price, Map<String, Integer> reciepe) {
		super();
		this.name = name;
		this.price = price;
		this.reciepe = reciepe;
	}

	public String getName() {
		return name;
	}

	public double getPrice() {
		return price;
	}

	public Map<String, Integer> getReciepe() {
		return reciepe;
	}
	
	
	

}
