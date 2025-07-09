package com.atm;

import java.util.HashMap;
import java.util.Map;

public class Account {

	private final String accountNumber;
	private double balance;
	private Map<String,Card> cards;
	
	public Account(String accountNumber, double balance) {
		super();
		this.accountNumber = accountNumber;
		this.balance = balance;
		this.cards = new HashMap<String, Card>();
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public Map<String, Card> getCards() {
		return cards;
	}

	public void setCards(Map<String, Card> cards) {
		this.cards = cards;
	}

	public String getAccountNumber() {
		return accountNumber;
	}
	
	
	public synchronized void deposit(double amount) {
		balance+=amount;
	}
	
	public synchronized boolean withdraw(double amount) {
		if(balance>=amount) {
			balance-=amount;
			return true;
		}
		return false;
	}
	
	
	
}
