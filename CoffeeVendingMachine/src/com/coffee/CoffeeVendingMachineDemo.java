package com.coffee;

public class CoffeeVendingMachineDemo {
	
	public static void main(String[] args) throws IllegalAccessException {
		
		CoffeeVendingMachine instance=CoffeeVendingMachine.getInstnace();
		
		instance.refilIngredient("Coffee",150);
		instance.refilIngredient("Water",100);
		instance.refilIngredient("Milk", 100);
		
		//display coffee Menu
		instance.displayMenu();
		
		//simulate user request
	CoffeeReciepe espresso=instance.selectCoffee("Espresso");
	instance.dispenseCoffee(espresso, new Payment(60.0));
	
	CoffeeReciepe latte=instance.selectCoffee("Latte");
	instance.dispenseCoffee(latte, new Payment(100.0));
	
	CoffeeReciepe cappuccino=instance.selectCoffee("Cappuccino");
	instance.dispenseCoffee(cappuccino, new Payment(120.0));

	}

}
