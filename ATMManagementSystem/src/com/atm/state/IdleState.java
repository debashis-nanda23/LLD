package com.atm.state;

import com.atm.ATM;
import com.atm.Card;
import com.atm.OperationType;

public class IdleState implements ATMState {

	@Override
	public void insertCard(ATM atm, String cardNumber) {
		System.out.println("Card has been inserted");
		Card card=atm.getCard(cardNumber);
		if(card!=null) {
			atm.setCurrentCard(card);
			atm.changeState(new HasCardState());
		}else {
			ejectCard(atm);
		}
		
	}

	@Override
	public void enterPIN(ATM atm, String pin) {
		System.out.println("Error: Please insert a crad first...");
		
	}

	@Override
	public void selectOperation(ATM artm, OperationType type, int[] args) {
		System.out.println("Error: Please insert a card first.....");
		
	}

	@Override
	public void ejectCard(ATM atm) {
		System.out.println("Error: Card not found");
		atm.setCurrentCard(null);
		
	}

}
