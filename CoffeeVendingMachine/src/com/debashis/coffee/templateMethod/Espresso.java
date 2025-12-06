package com.debashis.coffee.templateMethod;

import java.util.Map;

import com.debashis.coffee.decorator.Coffee;
import com.debashis.coffee.enums.CoffeeType;
import com.debashis.coffee.enums.Ingredient;

public class Espresso  extends Coffee{

	public Espresso() {
		this.coffeeType=CoffeeType.ESPRESSO.name();
	}
	
	@Override
	protected void addCondiments() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getPrice() {
		return 150;
	}

	@Override
	public Map<Ingredient, Integer> getReceipe() {
		return Map.of(Ingredient.COFFEE_BEANS,7,Ingredient.WATER,10);
	}

}
