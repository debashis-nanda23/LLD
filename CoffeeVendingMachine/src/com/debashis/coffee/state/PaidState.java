package com.debashis.coffee.state;

import com.debashis.coffee.decorator.Coffee;
import com.debashis.coffee.main.CoffeeVendingMachine;
import com.debashis.coffee.main.Inventory;

public class PaidState implements VendingMachineState {

	@Override
	public void selectCoffee(CoffeeVendingMachine machine, Coffee coffee) {
		System.out.println("Can not select another coffee now.");
		
	}

	@Override
	public void insertMoney(CoffeeVendingMachine machine, int amount) {
		System.out.println("Already paid. Please wait for your coffee.");
		
	}

	@Override
	public void dispenseCoffee(CoffeeVendingMachine machine) {
		Inventory inventory=Inventory.getInstance();
		Coffee coffeeToBeDispensed= machine.getSelectedCoffe();
		
		if(!inventory.hasIngredients(coffeeToBeDispensed.getReceipe())) {
			System.out.println("Sorry, Out of ingredients for "+machine.getSelectedCoffe().getCoffeeType());
			machine.setState(new OutOfIngredientState());
			machine.getState().cancel(machine);
			return;
		}
		
		inventory.deductIngredient(machine.getSelectedCoffe().getReceipe());
		coffeeToBeDispensed.prepare();
		int change=machine.getMoneyInserted()-coffeeToBeDispensed.getPrice();
		if(change>0) {
			System.out.println("Returning change: "+change);
		}
		machine.reset();
		machine.setState(new ReadyState());
		
	}

	@Override
	public void cancel(CoffeeVendingMachine machine) {
		new SelectingState().cancel(machine);
		
	}

}
