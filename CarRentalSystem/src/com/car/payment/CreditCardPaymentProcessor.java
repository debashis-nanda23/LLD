package com.car.payment;

public class CreditCardPaymentProcessor implements PaymentProcessor {

	@Override
	public boolean processPayment(double amount) {
		System.out.println("Payment done using credit card");
		return true;
	}

}
