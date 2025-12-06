package com.debashis.coffee.decorator;

import java.util.HashMap;
import java.util.Map;

import com.debashis.coffee.enums.Ingredient;

public class CaramelSyrupDecorator extends CoffeeDecorator {

	private static final int COST=30;
	private static final Map<Ingredient,Integer> RECEIPE_ADDITION=Map.of(Ingredient.CARAMEL_SYRUP,10);
	
	public CaramelSyrupDecorator(Coffee coffee) {
		super(coffee);
	}
	
	@Override
	public String getCoffeeType() {
		return decoratedCoffee.coffeeType+", Caramel syrup";
	}
	
	@Override
	public int getPrice() {
		return decoratedCoffee.getPrice()+COST;
	}
	
	@Override
	public Map<Ingredient, Integer> getReceipe() {
		Map<Ingredient,Integer> newReceipe=new HashMap<>(decoratedCoffee.getReceipe());
		RECEIPE_ADDITION.forEach((ingredient,qty)->newReceipe.merge(ingredient,qty,Integer::sum));
		return newReceipe;
	}
	
	@Override
	public void prepare() {
		
		super.prepare();
		System.out.println("\n Driziling caramel syrup on top .");
		
	}

}
