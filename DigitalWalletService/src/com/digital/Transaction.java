package com.digital;

import java.math.BigDecimal;
import java.time.LocalDateTime;


//It is in currency
//It invokes account
public class Transaction {
	
	private final String id;
	private final Account sourceAccount;
	private final Account destinationAccount;
	private final BigDecimal amount;
	private final com.digital.Cuurency currency;
	private final LocalDateTime timeStamp;
	
	public Transaction(String id, Account sourceAccount, Account destinationAccount, BigDecimal amount,Cuurency cuurency) {
		super();
		this.id = id;
		this.sourceAccount = sourceAccount;
		this.destinationAccount = destinationAccount;
		this.amount = amount;
		this.currency = cuurency;
		this.timeStamp=LocalDateTime.now();
	}

	public String getId() {
		return id;
	}

	public Account getSourceAccount() {
		return sourceAccount;
	}

	public Account getDestinationAccount() {
		return destinationAccount;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public com.digital.Cuurency getCurrency() {
		return currency;
	}

	public LocalDateTime getTimeStamp() {
		return timeStamp;
	}
	
	
	
	

}
