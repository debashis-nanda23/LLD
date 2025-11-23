package com.debashis.carrental;

public class CrditCardPaymentProcessor implements PaymentProcessor{

	@Override
	public boolean processPayment(double amount) {
		System.out.println("Payment done through credit card for the payment::"+amount);
		return true;
	}

}
