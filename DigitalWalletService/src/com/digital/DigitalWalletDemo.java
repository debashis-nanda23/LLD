package com.digital;

import java.math.BigDecimal;
import java.util.List;

public class DigitalWalletDemo {
	
	public static void main(String[] args) {
		
		DigitalWallet instance=DigitalWallet.getInstance();
		
		//create Users
		User user1=new User("U001","User 1", "user1@gmail.com", "user1@123");
		User user2=new User("U002","User 2", "user2@gmail.com", "user2@123");
		instance.createUser(user1);
		instance.createUser(user2);
		
		//create Accounts
		Account account1=new Account("A001","123456789", user1, Cuurency.EUR);
		Account account2=new Account("A002","987654321", user2, Cuurency.USD);
		instance.createAccount(account1);
		instance.createAccount(account2);
		
		//Add Payment Method
		PaymentMethod creditCard=new CreditCard("PM001", user1, "367845329852","387", "12/25");
		PaymentMethod bankAccount=new BankAccount("PM002", user2,"987654321", "9876543210");
		instance.addPaymentMethod(bankAccount);
		instance.addPaymentMethod(creditCard);
		
		//Deposit funds
		account1.deposite(new BigDecimal("1000.00"));
		account2.deposite(new BigDecimal("2000.00"));
		
		//Transfer Funds
		instance.transferFunds(account1, account2, new BigDecimal("500.00"),Cuurency.USD);
		
		//Get Transaction History
		List<Transaction> transactionHistiryAccount1= instance.getTransactionHistory(account1);
		List<Transaction> transactionHistoryAccount2=instance.getTransactionHistory(account2);
		
		//Print Transaction History
		System.out.println("Transaction Hsitry of Account 1:");
		for(Transaction transaction:transactionHistiryAccount1) {
			System.out.println("Transaction Id is:"+transaction.getId());
			System.out.println("Amount::"+transaction.getAmount()+":"+transaction.getCurrency());
			System.out.println("TimeStamp is::"+transaction.getTimeStamp());
			System.out.println();
		}
		
		
		System.out.println("Transaction Hsitry of Account 2:");
		for(Transaction transaction1:transactionHistoryAccount2) {
			System.out.println("Transaction Id is:"+transaction1.getId());
			System.out.println("Amount::"+transaction1.getAmount()+":"+transaction1.getCurrency());
			System.out.println("TimeStamp is::"+transaction1.getTimeStamp());
			System.out.println();
		}
		
	}

}
