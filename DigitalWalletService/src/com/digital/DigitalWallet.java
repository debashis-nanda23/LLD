package com.digital;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

//It manages User
//It manages Account
//It manages PaymentMehtod
public class DigitalWallet {
	
	private static DigitalWallet digitalWallet;
	private final Map<String,User> users;
	private final Map<String,Account> accounts;
	private final Map<String,PaymentMethod> paymentMethods;
	
	public static synchronized DigitalWallet getInstance() {
		if(digitalWallet==null) {
			digitalWallet=new DigitalWallet();
		}
		return digitalWallet;
	}
	
	private DigitalWallet() {
		this.users=new ConcurrentHashMap<String, User>();
		this.accounts=new ConcurrentHashMap<String, Account>();
		this.paymentMethods=new ConcurrentHashMap<String, PaymentMethod>();
	}
	
	public void createUser(User user) {
		this.users.put(user.getId(), user);
	}
	
	public User getUser(String userId) {
		return this.users.get(userId);
	}
	
	public void createAccount(Account account) {
		this.accounts.put(account.getId(),account);
	}
	
	public Account getAccount(String accountId) {
		return this.accounts.get(accountId);
	}
	
	public void addPaymentMethod(PaymentMethod paymentMethod) {
		this.paymentMethods.put(paymentMethod.getId(), paymentMethod);
	}
	
	public PaymentMethod getPaymentMethod(String paymentId) {
		return this.paymentMethods.get(paymentId);
	}
	

	public synchronized void transferFunds(Account sourecAccount,Account destinationAccount,BigDecimal amount,Cuurency currency) {
		
		if(sourecAccount.getCurrency()!=currency) {
			amount=CurrencyConverter.conver(amount, sourecAccount.getCurrency(),destinationAccount.getCurrency());
		}
		sourecAccount.withdrawl(amount);
		if(destinationAccount.getCurrency()!=currency) {
			amount=CurrencyConverter.conver(amount, sourecAccount.getCurrency(),destinationAccount.getCurrency());
		}
		destinationAccount.deposite(amount);
		
		String transactionId=generateTransactionId();
		Transaction transaction=new Transaction(transactionId, sourecAccount, destinationAccount, amount, destinationAccount.getCurrency());
		sourecAccount.addTranasaction(transaction);
		destinationAccount.addTranasaction(transaction);
		
	}

	private String generateTransactionId() {
		return "TX-"+UUID.randomUUID().toString().substring(0,8).toUpperCase();
	}
	
	public List<Transaction> getTransactionHistory(Account account){
		return account.getTransaction();
	}
}
