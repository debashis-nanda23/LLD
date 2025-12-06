package com.debashis.coffee.templateMethod;

import java.util.Map;

import com.debashis.coffee.decorator.Coffee;
import com.debashis.coffee.enums.CoffeeType;
import com.debashis.coffee.enums.Ingredient;

public class Cappuccino extends Coffee {

	public Cappuccino() {
		this.coffeeType=CoffeeType.CAPPUCCINO.name();
	}
	
	@Override
	protected void addCondiments() {
		System.out.println("- Adding steamed milk and foam");
		
	}

	@Override
	public int getPrice() {
		return 250;
	}

	@Override
	public Map<Ingredient, Integer> getReceipe() {
	  return Map.of(Ingredient.COFFEE_BEANS,7,Ingredient.WATER,30,Ingredient.MILK,100);	
	}

}
