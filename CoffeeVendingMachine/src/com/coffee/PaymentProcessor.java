package com.coffee;

public class PaymentProcessor {
	
	public double processPayment(double price,double paid) {
	   System.out.println("Processing Payment");
	   return paid-price;
	}

}
