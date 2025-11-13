package com.debashis.atm.chainofresponsibility;

public interface DispenseChain {

	public void setNextChain(DispenseChain nextChain);
	public void dispense(int amount);
	public boolean canDispense(int amount);
}
