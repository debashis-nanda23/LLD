package com.atm.dispenser;

public class NoteDispenser implements DispenserChain {

	private DispenserChain nextChain;
	private final int noteValue;
	private int numNotes;
	
	
	public NoteDispenser(int noteValue, int numNotes) {
		this.noteValue = noteValue;
		this.numNotes = numNotes;
	}

	@Override
	public void setNextChain(DispenserChain nextChain) {
		this.nextChain=nextChain;
		
	}

	@Override
	public synchronized void dispense(int amount) {
	
		if(amount>=this.noteValue) {
			int numToDispense=Math.min(amount/noteValue, this.numNotes);
			int remaining=amount-(numToDispense*noteValue);
			
			if(numToDispense>0) {
				System.out.println("Dispensing "+numToDispense+" x $"+noteValue+" note(s)");
				this.numNotes-=numToDispense;
			}
			if(remaining>0 && this.nextChain!=null) {
				this.nextChain.dispense(remaining);
			}
		}else if(this.nextChain!=null) {
			this.nextChain.dispense(amount);
		}
	}

	@Override
	public boolean canDispense(int amount) {
		if(amount<0)  return false;
		if(amount==0)  return true;
		
		int numToDispense=Math.min(amount/noteValue, this.numNotes);
		int remainingNotes=amount-(numToDispense*noteValue);
		
		if(remainingNotes==0)  return true;
		if(this.nextChain!=null){
			return this.nextChain.canDispense(remainingNotes);
		}
		return false;
	}

}
