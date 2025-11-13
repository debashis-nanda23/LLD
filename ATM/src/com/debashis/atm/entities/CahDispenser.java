package com.debashis.atm.entities;

import com.debashis.atm.chainofresponsibility.DispenseChain;

public class CahDispenser {

	private final DispenseChain chain;

	public CahDispenser(DispenseChain chain) {
		super();
		this.chain = chain;
	}
	
	
	public synchronized void dispnseCash(int amount) {
		this.chain.dispense(amount);
	}
	
	public synchronized boolean canDispenseCash(int amount) {
		if(amount%10!=0) {
			return false;
		}
		return this.chain.canDispense(amount);
	}
}
