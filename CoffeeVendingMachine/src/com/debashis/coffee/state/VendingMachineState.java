package com.debashis.coffee.state;

import com.debashis.coffee.decorator.Coffee;
import com.debashis.coffee.main.CoffeeVendingMachine;

public interface VendingMachineState {
	
	void selectCoffee(CoffeeVendingMachine machine,Coffee coffee);
	void insertMoney(CoffeeVendingMachine machine,int amount);
	void dispenseCoffee(CoffeeVendingMachine machine);
	void cancel(CoffeeVendingMachine machine);

}
