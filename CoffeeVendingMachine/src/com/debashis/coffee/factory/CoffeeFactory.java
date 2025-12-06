package com.debashis.coffee.factory;

import com.debashis.coffee.decorator.Coffee;
import com.debashis.coffee.enums.CoffeeType;
import com.debashis.coffee.templateMethod.Cappuccino;
import com.debashis.coffee.templateMethod.Espresso;
import com.debashis.coffee.templateMethod.Latte;

public class CoffeeFactory {

	public static Coffee createCoffee(CoffeeType type) {
		switch(type) {
		case ESPRESSO:
			return new Espresso();
		case LATTE:
			return new Latte();
		case CAPPUCCINO:
			return new Cappuccino();
		default:
			throw new IllegalArgumentException("Unsupported coffee type: "+type);
		}
	}
}
