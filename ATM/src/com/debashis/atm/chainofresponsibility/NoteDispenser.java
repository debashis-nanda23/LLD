package com.debashis.atm.chainofresponsibility;

public class NoteDispenser implements DispenseChain{

	private DispenseChain dispenseChain;
	private final int noteValue;
	private int numNotes;
	
	public NoteDispenser(int noteValue,int numNotes) {
		this.noteValue=noteValue;
		this.numNotes=numNotes;
	}
	
	@Override
	public void setNextChain(DispenseChain nextChain) {
		this.dispenseChain=nextChain;
		
	}

	@Override
	public synchronized void dispense(int amount) {
		if(amount>=noteValue) {
			int numToDispense=Math.min(amount/noteValue, numNotes);
			int remainingAmount=amount-(numToDispense*noteValue);
			
			if(numToDispense>0) {
				System.out.println("Dispensing "+numToDispense+" * "+noteValue+" notes");
				this.numNotes-=numToDispense;
			}
			
			if(remainingAmount>0 && this.dispenseChain!=null) {
				this.dispenseChain.dispense(remainingAmount);
			}
		}else if(this.dispenseChain!=null) {
			this.dispenseChain.dispense(amount);
		}
		
	}

	@Override
	public synchronized boolean canDispense(int amount) {
		
		if(amount<0)  return false;
		if(amount==0)  return true;
		
		int numToUse=Math.min(amount/noteValue,numNotes);
		int remainingAmount=amount-(noteValue*numToUse);
		
		if(remainingAmount==0) return true;
		if(this.dispenseChain!=null) {
			return this.canDispense(remainingAmount);
		}
		
		return false;
		

	}

}
