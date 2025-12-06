package com.debashis.coffee.decorator;

import java.util.Map;

import com.debashis.coffee.enums.Ingredient;

public abstract class CoffeeDecorator extends Coffee {
	
	protected Coffee decoratedCoffee;
	
	public CoffeeDecorator(Coffee coffee) {
		this.decoratedCoffee=coffee;
	}
	
	@Override
	public int getPrice() {
		return decoratedCoffee.getPrice();
	}
	
	@Override
	public Map<Ingredient, Integer> getReceipe() {
		return decoratedCoffee.getReceipe();
	}
	
	@Override
	protected void addCondiments() {
		decoratedCoffee.addCondiments();
	}
	
	@Override
	public void prepare() {
		decoratedCoffee.prepare();
	}
	

}
