package com.debashis.atm.state;

import com.debashis.atm.ATMSystem;
import com.debashis.atm.enums.OperationType;

public class AuthenticateState implements ATMState{

	@Override
	public void insertCard(ATMSystem atmSystem, String cardNumber) {
	    System.out.println("Error: A card has alrwady been insrted and session is active.");
		
	}

	@Override
	public void enterPin(ATMSystem atmSystem, String pin) {
		System.out.println("Error: PIN has already been entered and authenticated");
		
	}

	@Override
	public void selectOperation(ATMSystem atmSystem, OperationType op, int... args) {
		
		switch(op) {
		case CHECK_BALANCE:
			atmSystem.checkBalance();
			break;
		case WITHDRAW_CASH:
			if(args.length==0 || args[0]<=0) {
				System.out.println("Error: Invalid withdrawal amount specified.");
				break;
			}
			
			int amountToBeWithdrawan=args[0];
			Double accountBalance=atmSystem.getBakService().getBalance(atmSystem.getCurrentCard());
			if(accountBalance<amountToBeWithdrawan) {
				System.out.println("Error: Insufficient balance.");
				break;
			}
			
			System.out.println("Processing withdrawl for "+amountToBeWithdrawan);
			atmSystem.withdrawCash(amountToBeWithdrawan);
			break;
			
		case DEPOSIT_CACH:
			if(args.length==0 || args[0]<=0) {
				System.out.println("Error: Invalid withdrawal amount specified.");
				break;
			}
			int amountToBeDeposited=args[0];
			System.out.println("Processing deposit for "+amountToBeDeposited);
			atmSystem.depositCash(amountToBeDeposited);
			break;
		default:
		   System.out.println("Error: Invalid Operation type selected.");
		   break;
		}
		
	}

	@Override
	public void ejectCard(ATMSystem atmSystem) {
		System.out.println("Ending session. Card has been ejected. Thank you for suing ATM");
		atmSystem.setCurretnCard(null);
		atmSystem.changeState(new IdleState());
		
	}

}
