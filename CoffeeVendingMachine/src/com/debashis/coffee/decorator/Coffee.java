package com.debashis.coffee.decorator;

import java.util.Map;

import com.debashis.coffee.enums.Ingredient;

public abstract class Coffee {

	protected String coffeeType="Unkonown Coffee";

	public String getCoffeeType() {
		return coffeeType;
	}
	
	public void prepare() {
		System.out.println("\n Preparing your "+this.coffeeType+".....");
		gridBeans();
		brew();
		addCondiments();
		pourIntoCup();
		System.out.println(this.coffeeType+" is ready!");
	}
	
	protected abstract void addCondiments();

	private void gridBeans() {
		System.out.println("- Griding fresh coffee Beans.");
	}
	
	private void brew() {
		System.out.println("- Brewing coffee with hot water.");
	}
	
	private void pourIntoCup() {
		System.out.println("- Pouring into a cup.");
	}
	
	public abstract int getPrice();
	public abstract Map<Ingredient,Integer> getReceipe();
	
	
}
