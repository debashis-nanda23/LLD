package com.digital;

import java.math.BigDecimal;

//It implements PaymentMethod
public class BankAccount extends PaymentMethod {

	private final String accountNumber;
	private final String routingNumber;
	
	public BankAccount(String id, User user, String accountNumber, String routingNumber) {
		super(id, user);
		this.accountNumber = accountNumber;
		this.routingNumber = routingNumber;
	}

	@Override
	public boolean processPayment(BigDecimal amount, Cuurency currency) {
		System.out.println("Payment done from bank ccount of amount "+amount+" of currency "+currency.name());
		return true;
	}
	
}
