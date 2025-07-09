package com.atm.dispenser;

public interface DispenserChain {
	
	void setNextChain(DispenserChain nextChain);
	void dispense(int amount);
	boolean canDispense(int amount);

}
