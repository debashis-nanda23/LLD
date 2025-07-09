package com.atm;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class BankService {
	
	private final Map<String,Card> cards=new ConcurrentHashMap<>();
	private final Map<String,Account> accounts=new ConcurrentHashMap<>();
	private final Map<Card,Account> cardAccount=new ConcurrentHashMap<>();
	
	public BankService() {
		Account account1=createAccount("123456789", 10000.0);
		Card card1=createCard("1234-5678-1409","7978");
		linkCardToAccount(card1, account1);
		
		Account account2=createAccount("987654321", 10000.0);
		Card card2=createCard("1234-5678-1831","6023");
		linkCardToAccount(card2, account2);
		
		
	}

	public Account createAccount(String accountNumber,double initialBalance) {
		Account account=new Account(accountNumber, initialBalance);
		accounts.put(accountNumber, account);
		return account;
	}
	
	public Card createCard(String cardNumber,String pin) {
		Card card=new Card(cardNumber,pin);
		cards.put(cardNumber, card);
		return card;
	}
	
	public void linkCardToAccount(Card card,Account account) {
		account.getCards().put(card.getCardNumber(),card);
		cardAccount.put(card, account);
	}
	
	public boolean authenticate(Card card,String pin) {
		return card.getPin().equals(pin);
	}
	
	public Card getCard(String cardNumber) {
		return cards.getOrDefault(cardNumber, null);
	}
	
	public double getBalance(Card card) {
		return cardAccount.get(card).getBalance();
	}
	
	public void withdrawMoney(Card card,double balance) {
		cardAccount.get(card).withdraw(balance);
	}
	
	public void depositMoney(Card card,double balance) {
		cardAccount.get(card).deposit(balance);
	}
}
