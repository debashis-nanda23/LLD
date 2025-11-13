package com.debashis.atm.state;

import com.debashis.atm.ATMSystem;
import com.debashis.atm.entities.Card;
import com.debashis.atm.enums.OperationType;

public class IdleState implements ATMState {

	@Override
	public void insertCard(ATMSystem atmSystem, String cardNumber) {
		
		System.out.println("\nCard has been inserted");
		Card card=atmSystem.getCard(cardNumber);
		if(card==null) {
			ejectCard(atmSystem);
		}else {
			atmSystem.setCurretnCard(card);
			atmSystem.changeState(new HasCardState());
		}
	}

	@Override
	public void enterPin(ATMSystem atmSystem, String pin) {
		System.out.println("Plase insert a card first.");
		
	}

	@Override
	public void selectOperation(ATMSystem atmSystem, OperationType op, int... args) {
		System.out.println("Plase insert a card first.");
		
	}

	@Override
	public void ejectCard(ATMSystem atmSystem) {
		System.out.println("Error: Card not found");
		atmSystem.setCurretnCard(null);
		
	}

}
