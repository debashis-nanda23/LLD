package com.debashis.coffee.state;

import com.debashis.coffee.decorator.Coffee;
import com.debashis.coffee.main.CoffeeVendingMachine;

public class SelectingState implements VendingMachineState {

	@Override
	public void selectCoffee(CoffeeVendingMachine machine, Coffee coffee) {
		System.out.println("Alreadt selected , Please pay or cancel");
		
	}

	@Override
	public void insertMoney(CoffeeVendingMachine machine, int amount) {
		machine.setMoneyInserted(machine.getMoneyInserted()+amount);
		System.out.println("Inserted: "+amount+". Total: "+machine.getMoneyInserted());
		if(machine.getMoneyInserted()>=machine.getSelectedCoffe().getPrice()) {
			machine.setState(new PaidState());
		}
		
	}

	@Override
	public void dispenseCoffee(CoffeeVendingMachine machine) {
		System.out.println("Please insert enough money first.");
		
	}

	@Override
	public void cancel(CoffeeVendingMachine machine) {
		System.out.println("Transaction cancelled. Refunding "+machine.getMoneyInserted());
		machine.reset();
		machine.setState(new ReadyState());
		
	}

}
