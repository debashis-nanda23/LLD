package com.debashis.coffee.main;

import java.util.List;

import com.debashis.coffee.enums.CoffeeType;
import com.debashis.coffee.enums.Ingredient;
import com.debashis.coffee.enums.ToppingType;

public class CoffeVendingMachoneDemo {
	
	public static void main(String[] args) {
		
		CoffeeVendingMachine machine=CoffeeVendingMachine.getInstance();
		Inventory inventory=Inventory.getInstance();
		
		//Refill Inventory
		System.out.println("=======Initializing Vending Machine=====");
		inventory.addStock(Ingredient.COFFEE_BEANS,50);
		inventory.addStock(Ingredient.WATER, 500);
		inventory.addStock(Ingredient.MILK, 200);
		inventory.addStock(Ingredient.SUGAR, 100);
		inventory.addStock(Ingredient.CARAMEL_SYRUP,50);
		inventory.printInventory();
		
		//Successful purchase of Latte
		System.out.println("===Buying a latte=====");
		machine.selectCoffee(CoffeeType.LATTE, List.of());
		machine.insertMoney(200);
		machine.insertMoney(50);
		machine.dispenseCoffee();
		inventory.printInventory();
		
		//purchase with insufficient funds and cancellation
		System.out.println("=====Buying a Cappuccino=======");
		inventory.printInventory();
		machine.selectCoffee(CoffeeType.CAPPUCCINO,List.of(ToppingType.CARAMEL_SYRUP,ToppingType.EXTRA_SUGAR));
		machine.insertMoney(300);
		machine.dispenseCoffee();
		inventory.printInventory();
		
		//refill and final test
		System.out.println("===Refilling and final test========");
		inventory.addStock(Ingredient.MILK, 200);
		inventory.printInventory();
		machine.selectCoffee(CoffeeType.LATTE,List.of(ToppingType.CARAMEL_SYRUP));
		machine.insertMoney(250);
		machine.dispenseCoffee();
		inventory.printInventory();
	}

}
