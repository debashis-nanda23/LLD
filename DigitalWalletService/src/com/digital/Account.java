package com.digital;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

//It belongs to user
//It records Transaction
public class Account {
	
	private final String id;
	private final String accountNumber;
	private final User user;
	private final Cuurency currency;
	private final BigDecimal balance;
	private final List<Transaction> transaction;
	
	public Account(String id, String accountNumber, User user, Cuurency currency) {
		super();
		this.id = id;
		this.accountNumber = accountNumber;
		this.user = user;
		this.currency = currency;
		this.transaction=new ArrayList<Transaction>();
		this.balance=new BigDecimal(1000);
	}
	
	public synchronized void deposite(BigDecimal amount) {
		this.balance.add(amount);
	}
	
	public synchronized void withdrawl(BigDecimal amount) {
		if(this.balance.compareTo(amount)<0) {
			throw new InssuficientFundsException("Insuffcient Funfs for withdrawal");
		}
		this.balance.subtract(amount);
	}
	
	public synchronized void addTranasaction(Transaction transaction) {
		this.transaction.add(transaction);
	}

	public String getId() {
		return id;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public User getUser() {
		return user;
	}

	public Cuurency getCurrency() {
		return currency;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public List<Transaction> getTransaction() {
		return transaction;
	}
	

}
