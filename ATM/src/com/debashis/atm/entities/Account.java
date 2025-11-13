package com.debashis.atm.entities;

import java.util.HashMap;
import java.util.Map;

public class Account {
	
	private final String accountNumber;
	private double balance;
	private final Map<String,Card> cards;
	
	public Account(String accountNumber, double balance) {
		super();
		this.accountNumber = accountNumber;
		this.balance = balance;
		this.cards = new HashMap<>();
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public double getBalance() {
		return balance;
	}

	public Map<String, Card> getCards() {
		return cards;
	}
	
	public synchronized void deposit(double amount) {
		this.balance=this.balance+amount;
	}
	
	public synchronized boolean withdraw(double amount) {
		if(amount<=balance) {
			this.balance-=amount;
			return true;
		}
		return false;
		
	}

}
