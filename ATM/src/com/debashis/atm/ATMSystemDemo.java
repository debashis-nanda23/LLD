package com.debashis.atm;

import com.debashis.atm.enums.OperationType;

public class ATMSystemDemo {
	
	public static void main(String[] args) {
		
		ATMSystem atmSystem=ATMSystem.getInstance();
		
		//perform check balance operation
		atmSystem.insertCard("1234-5678-4321-9876");
		atmSystem.enterPin("7978");
		atmSystem.selectOperation(OperationType.CHECK_BALANCE);
		System.out.println("========");
		
		//perform withdraw cash operation
		atmSystem.insertCard("1234-5678-4321-9876");
		atmSystem.enterPin("7978");
		atmSystem.selectOperation(OperationType.WITHDRAW_CASH,2000);
		System.out.println("========");
		
		//perform deposit operation
		atmSystem.insertCard("1234-5678-4321-9876");
		atmSystem.enterPin("7978");
		atmSystem.selectOperation(OperationType.DEPOSIT_CACH, 3000);
		System.out.println("========");
		
		//perform check balance operation
				atmSystem.insertCard("1234-5678-4321-9876");
				atmSystem.enterPin("7978");
				atmSystem.selectOperation(OperationType.CHECK_BALANCE);
				System.out.println("========");
				
				//perform withdraw cash operation
				atmSystem.insertCard("1234-5678-4321-9876");
				atmSystem.enterPin("7978");
				atmSystem.selectOperation(OperationType.WITHDRAW_CASH,12000);
				System.out.println("========");
		
		
		
		
	}

}
