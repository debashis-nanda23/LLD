package com.atm;

import com.atm.dispenser.DispenserChain;

public class CashDispenser {
	
	private final DispenserChain chain;

	public CashDispenser(DispenserChain chain) {
		this.chain = chain;
	}
	
	public synchronized void dispneseCash(int amount) {
		chain.dispense(amount);
	}
	
	public synchronized boolean canDispense(int amount) {
		if(amount%10!=0) {
			return false;
		}
		return chain.canDispense(amount);
	}
	
	

}
