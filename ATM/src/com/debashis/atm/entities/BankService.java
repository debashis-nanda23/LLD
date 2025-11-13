package com.debashis.atm.entities;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class BankService {
	
	private final Map<String,Card> cards=new ConcurrentHashMap<>();
	private final Map<String,Account> accounts=new ConcurrentHashMap<>();
	private final Map<Card,Account> cardAccountMap=new ConcurrentHashMap<>();
	
	public BankService() {
		
		Account account1=createAccount("1234567890", 10000.00);
		Card card1=createCard("1234-5678-4321-9876","7978");
		linkCardToAccount(card1, account1);
		
		Account account2=createAccount("0987654321", 20000.00);
		Card card2=createCard("9876-5432-1234-5678","6023");
		linkCardToAccount(card2, account2);
		
		
	}

	public Account createAccount(String accountNumber,double initialBalance) {
		Account account=new Account(accountNumber, initialBalance);
		this.accounts.put(accountNumber, account);
		return account;
	}
	
	public Card createCard(String cardNumber,String pin) {
		Card card=new Card(cardNumber, pin);
		this.cards.put(cardNumber, card);
		return card;
	}
	
	public void linkCardToAccount(Card card,Account account) {
		account.getCards().put(card.getCardNumber(), card);
		this.cardAccountMap.put(card, account);
	}
	
	public boolean authenticate(Card card,String pin) {
		return card.getPin().equals(pin);
	}
	
	public Card getCard(String cardNumber) {
		return this.cards.getOrDefault(cardNumber, null);
	}
	
	public double getBalance(Card card) {
		return cardAccountMap.get(card).getBalance();
	}
	
	public void withdrawMoney(Card card,double amount) {
		this.cardAccountMap.get(card).withdraw(amount);
	}
	
	public void depositMoney(Card card,double amount) {
		this.cardAccountMap.get(card).deposit(amount);
	}
	
	
	
}
