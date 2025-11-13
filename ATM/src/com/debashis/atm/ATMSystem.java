package com.debashis.atm;

import java.util.concurrent.atomic.AtomicInteger;

import com.debashis.atm.chainofresponsibility.DispenseChain;
import com.debashis.atm.chainofresponsibility.NoteDispenser100;
import com.debashis.atm.chainofresponsibility.NoteDispneser10;
import com.debashis.atm.chainofresponsibility.NoteDispneser50;
import com.debashis.atm.entities.BankService;
import com.debashis.atm.entities.CahDispenser;
import com.debashis.atm.entities.Card;
import com.debashis.atm.enums.OperationType;
import com.debashis.atm.state.ATMState;
import com.debashis.atm.state.IdleState;

public class ATMSystem {
	
	private static ATMSystem instance;
	private final BankService bankService;
	private final CahDispenser cashDispenser;
	private ATMState currentState;
	private Card currentCard;
	private static final AtomicInteger transactionCounter=new AtomicInteger(0);
	
	private ATMSystem() {
		this.currentState=new IdleState();
		this.bankService=new BankService();
		
		DispenseChain c1=new NoteDispenser100(10);
		DispenseChain c2=new NoteDispneser50(20);
		DispenseChain c3=new NoteDispneser10(30);
		c1.setNextChain(c2);
		c2.setNextChain(c3);
		this.cashDispenser=new CahDispenser(c1);
		
	}
	
	
	public static synchronized ATMSystem getInstance() {
		if(instance==null) {
			instance=new ATMSystem();
		}
		return instance;
	}
	
	public void changeState(ATMState newState) {
		this.currentState=newState;
	}
	
	public void setCurretnCard(Card card) {
		this.currentCard=card;
	}
	
	public void insertCard(String cardNumber) {
		this.currentState.insertCard(instance, cardNumber);
	}
	
	public void enterPin(String pin) {
	   this.currentState.enterPin(instance, pin);	
	}
	
	public void selectOperation(OperationType op,int...args) {
		this.currentState.selectOperation(instance, op, args);
	}
	
	public Card getCard(String cardNumber) {
		return this.bankService.getCard(cardNumber);
	}

	public boolean authenticate(String pin) {
		return this.bankService.authenticate(currentCard, pin);
	}
	public void checkBalance() {
		Double balance=this.bankService.getBalance(currentCard);
		System.out.printf("Your current balacne is : $%.2f%n",balance);
	}
	
	public void withdrawCash(int amount) {
		if(!cashDispenser.canDispenseCash(amount)) {
			throw new IllegalStateException("Insufficent cash available in the atm.");
		}
		this.bankService.withdrawMoney(currentCard, amount);
		try {
			this.cashDispenser.dispnseCash(amount);
		}catch(Exception ex) {
           this.bankService.depositMoney(currentCard, amount);			
		}
	}
	
	public void depositCash(int amount) {
		this.bankService.depositMoney(currentCard, amount);
	}
	
	public Card getCurrentCard() {
		return currentCard;
	}
	
	public BankService getBakService() {
		return bankService;
	}
}
