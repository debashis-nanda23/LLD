package com.digital;

import java.math.BigDecimal;

public  abstract class PaymentMethod {

	protected final String id;
	protected final User user;
	
	public PaymentMethod(String id, User user) {
		super();
		this.id = id;
		this.user = user;
	}
	
	public abstract boolean processPayment(BigDecimal amount,Cuurency currency);

	public String getId() {
		return id;
	}
	
}
