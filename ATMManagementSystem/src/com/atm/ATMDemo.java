package com.atm;

public class ATMDemo {
	
	public static void main(String[] args) {
		
		ATM atm=ATM.getInstance();
		
		//perform check balance operation
		atm.insertCard("1234-5678-1409");
		atm.enterPin("7978");
		atm.selectOperation(OperationType.CHECK_BALANCE);
		
		System.out.println("==================>");
		//perform withdraw operation
		atm.insertCard("1234-5678-1409");
		atm.enterPin("7978");
		atm.selectOperation(OperationType.WITHDRAW_CAHS, 570);
		System.out.println("================>");
		
		//perform deposit operation
		atm.insertCard("1234-5678-1409");
		atm.enterPin("7978");
		atm.selectOperation(OperationType.DEPOSIT_CASH, 200);
		System.out.println("============>");
		//perform check balance operation
				atm.insertCard("1234-5678-1409");
				atm.enterPin("7978");
				atm.selectOperation(OperationType.CHECK_BALANCE);
				
	}
	

}
