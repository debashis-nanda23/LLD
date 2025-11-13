package com.debashis.atm.entities;

public class Card {
	
	private final String cardNumber;
	private final String pin;
	
	public String getCardNumber() {
		return cardNumber;
	}

	public String getPin() {
		return pin;
	}

	public Card(String cardNumber, String pin) {
		super();
		this.cardNumber = cardNumber;
		this.pin = pin;
	}
	
	

}
