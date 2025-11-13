package com.debashis.atm.state;

import com.debashis.atm.ATMSystem;
import com.debashis.atm.enums.OperationType;

public interface ATMState {

	void insertCard(ATMSystem atmSystem,String cardNumber);
	void enterPin(ATMSystem atmSystem,String pin);
	void selectOperation(ATMSystem atmSystem,OperationType op,int... args);
	void ejectCard(ATMSystem atmSystem);
}
