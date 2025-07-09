package com.atm.state;

import com.atm.ATM;
import com.atm.OperationType;

public class AuthenticatedState implements ATMState {

	@Override
	public void insertCard(ATM atm, String cardNumber) {
		System.out.println("Error: A card is already inserted and session is active");
		
	}

	@Override
	public void enterPIN(ATM atm, String pin) {
		System.out.println("Error: PIN has already been entered and authenticated");
		
	}

	@Override
	public void selectOperation(ATM atm, OperationType type, int[] args) {
		switch(type) {
		case CHECK_BALANCE:
			atm.checkBalance();
			break;
		case WITHDRAW_CAHS:
			if(args.length==0 || args[0]<=0) {
				System.out.println("Error: Invalid Withdraw amount specified");
				break;
			}
			int amountToWithdraw=args[0];
		    double accountBalance=atm.getBankService().getBalance(atm.getCurrentCard());
		    if(accountBalance< amountToWithdraw) {
		    	System.out.println("Errror: Insufficient Balance");
		    	break;
		    }
		    System.out.println("processing withdraw amount "+amountToWithdraw);
		    atm.withDrawCash(amountToWithdraw);
		    break;
		case DEPOSIT_CASH:
			if(args.length==0 || args[0]<=0) {
				System.out.println("Errro: Inavlid Deposit amoutn specified");
				break;
			}
			int amountToDeposit=args[0];
			System.out.println("processing deposit amount "+amountToDeposit);
			atm.depositeCach(amountToDeposit);
			break;
		default:
			System.out.println("Invalid Operation selected");
			break;
		
		}
		System.out.println("Transaction Complete.");
		ejectCard(atm);
		
	}

	@Override
	public void ejectCard(ATM atm) {
		System.out.println("Ending session. card has been ejected. Thank you for using ATM");
		atm.setCurrentCard(null);
		atm.changeState(new IdleState());
		
	}

}
