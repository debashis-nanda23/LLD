package com.debashis.carrental;

public class PayPalPaymentProcessor implements PaymentProcessor {

	@Override
	public boolean processPayment(double amount) {
	     System.out.println("Payment done through paypal thrid party for the amount ::"+amount);
	     return true;
	}

}
