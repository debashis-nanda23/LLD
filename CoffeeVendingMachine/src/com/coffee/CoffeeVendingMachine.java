package com.coffee;

import java.util.HashMap;
import java.util.Map;

public class CoffeeVendingMachine {
	
	private static final CoffeeVendingMachine instance=new CoffeeVendingMachine();
	private final IngredientStore ingredirnetStore;
	private  final PaymentProcessor paymentProcessor;
	private  final Dispenser dispenser;
	private  final Map<String,CoffeeReciepe> receipes=new HashMap<>();
	
	private  CoffeeVendingMachine() {
		ingredirnetStore=new IngredientStore();
		paymentProcessor=new PaymentProcessor();
		dispenser=new Dispenser();
		addDefaultReceipes();
		
	}

	private void addDefaultReceipes() {
		receipes.put("Espresso",new CoffeeReciepe("Espresso",50, Map.of("Coffee",50,"Water",30)));
		receipes.put("Latte",new CoffeeReciepe("Latte",80, Map.of("Coffee",50,"Water",20,"Milk",30)));
		receipes.put("Cappuccino",new CoffeeReciepe("Cappuccino",50, Map.of("Coffee",50,"Water",30,"Milk",40)));
		
	}
	
	public static CoffeeVendingMachine getInstnace() {
		return instance;
	}
	
	public void addReceipes(String name,double price,Map<String,Integer> recipe) {
		receipes.put(name, new CoffeeReciepe(name, price, recipe));
	}
	
	public void displayMenu() {
		System.out.println("Coffee Menu");
		for(String recipe:receipes.keySet()) {
			System.out.println(recipe+" with price "+receipes.get(recipe).getPrice());
		}
	}
	
	
	public synchronized CoffeeReciepe selectCoffee(String coffeeName) throws IllegalAccessException {
		if(!receipes.containsKey(coffeeName)){
			throw new IllegalAccessException("Invalid selection of coffee "+coffeeName);
		}
		return receipes.get(coffeeName);
	}

	public synchronized void dispenseCoffee(CoffeeReciepe recipe,Payment payment) throws IllegalAccessException {
		if(recipe.getPrice()>payment.getAmount()) {
			throw new IllegalAccessException("Insufficient funds for the coffee "+recipe.getName());
		}
		
		if(!ingredirnetStore.hasEnoughIngreident(recipe.getReciepe())) {
			throw new IllegalAccessException("Insufficient ingredients to make coffee "+recipe.getName());
		}
		
		ingredirnetStore.consume(recipe.getReciepe());
		dispenser.prepareCoffee(recipe);
		
		double change=paymentProcessor.processPayment(recipe.getPrice(), payment.getAmount());
		if(change>0) {
			System.out.println("Please collect your change "+change);
		}
	}
	
	public void refilIngredient(String ingredient,int quantity) {
		ingredirnetStore.refill(ingredient, quantity);
	}
	
	public void showAllIngredient() {
		System.out.println("Ingredient Levels:");
		ingredirnetStore.getAllIngredients().forEach((k,v)->System.out.println(k+":"+v));
	}
}
