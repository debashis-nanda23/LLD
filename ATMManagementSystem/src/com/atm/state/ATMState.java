package com.atm.state;

import com.atm.ATM;
import com.atm.OperationType;

public interface ATMState {

	void insertCard(ATM atm,String cardNumber);
	void enterPIN(ATM atm,String pin);
	void selectOperation(ATM artm,OperationType type,int[] args);
	void ejectCard(ATM atm);
}
