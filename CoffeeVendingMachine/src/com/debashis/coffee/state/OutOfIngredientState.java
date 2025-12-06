package com.debashis.coffee.state;

import com.debashis.coffee.decorator.Coffee;
import com.debashis.coffee.main.CoffeeVendingMachine;

public class OutOfIngredientState implements VendingMachineState {

	@Override
	public void selectCoffee(CoffeeVendingMachine machine, Coffee coffee) {
		System.out.println("Sorry this machine is out of ingredients.");
		
	}

	@Override
	public void insertMoney(CoffeeVendingMachine machine, int amount) {
		System.out.println("Sorry this machine is out of ingredients. Money will be refunded");
		
	}

	@Override
	public void dispenseCoffee(CoffeeVendingMachine machine) {
		System.out.println("Sorry this machine is out of ingredients.");
		
	}

	@Override
	public void cancel(CoffeeVendingMachine machine) {
		System.out.println("Refundinng "+machine.getMoneyInserted());
		machine.reset();
		machine.setState(new ReadyState());
		
	}

}
