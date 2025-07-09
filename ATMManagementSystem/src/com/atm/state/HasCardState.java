package com.atm.state;

import com.atm.ATM;
import com.atm.OperationType;

public class HasCardState implements ATMState {

	@Override
	public void insertCard(ATM atm, String cardNumber) {
		System.out.println("Error: A card has already been inserted. Can not insert another card");
		
	}

	@Override
	public void enterPIN(ATM atm, String pin) {
		System.out.println("Authneticating PIN..........");
		boolean isAuthgenticated=atm.authenticate(pin);
		if(isAuthgenticated) {
			System.out.println("Authentication successful");
			atm.changeState(new AuthenticatedState());
		}else {
			System.out.println("Errro: Authentication Failed. Please enter correct PIN......");
			ejectCard(atm);
		}
		
	}

	@Override
	public void selectOperation(ATM artm, OperationType type, int[] args) {
		System.out.println("Error: Please enter your correct pin to start a operation....");
		
	}

	@Override
	public void ejectCard(ATM atm) {
		System.out.println("Card has been ejected . Thank you for using ATM.");
		atm.setCurrentCard(null);
		atm.changeState(new IdleState());
		
	}

}
