package com.car.payment;

public class PayPalPaymentProcessor implements PaymentProcessor {

	@Override
	public boolean processPayment(double amount) {
		System.out.println("Payment using paypal customer support");
		return true;
	}

}
