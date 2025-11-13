package com.debashis.atm.state;

import com.debashis.atm.ATMSystem;
import com.debashis.atm.enums.OperationType;

public class HasCardState implements ATMState {

	@Override
	public void insertCard(ATMSystem atmSystem, String cardNumber) {
		System.out.println("Error: A card ha s already been inserted. Cannot insert another card");
		
	}

	@Override
	public void enterPin(ATMSystem atmSystem, String pin) {
		System.out.println("Authenticating PIN....");
		boolean isAuthenticated=atmSystem.authenticate(pin);
		if(isAuthenticated) {
			System.out.println("Authentication successful.");
			atmSystem.changeState(new AuthenticateState());
		}else {
			System.out.println("Authentication failed.Incorrect PIN.");
			ejectCard(atmSystem);
		}
		
	}

	@Override
	public void selectOperation(ATMSystem atmSystem, OperationType op, int... args) {
       System.out.println("Error: Please enter a pin first to perform an operation");		
	}

	@Override
	public void ejectCard(ATMSystem atmSystem) {
		System.out.println("Card has been ejected. Thank you for using ATM.");
		atmSystem.setCurretnCard(null);
		atmSystem.changeState(new IdleState());
		
	}

}
