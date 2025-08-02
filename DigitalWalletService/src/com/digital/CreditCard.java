package com.digital;

import java.math.BigDecimal;

//It implements PaymentMethod
public class CreditCard extends PaymentMethod{
	
	private final String cardNumber;
	private final String cvv;
	private final String expiryDate;
	
	
	public CreditCard(String id, User user, String cardNumber, String cvv, String expiryDate) {
		super(id, user);
		this.cardNumber = cardNumber;
		this.cvv = cvv;
		this.expiryDate = expiryDate;
	}


	@Override
	public boolean processPayment(BigDecimal amount, Cuurency currency) {
		System.out.println("Payment done with credit card of "+amount+" of cuurency "+currency.name()+" has done");
		return true;
	}

	
	
	
}
