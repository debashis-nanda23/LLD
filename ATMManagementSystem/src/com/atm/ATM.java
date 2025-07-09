package com.atm;

import java.util.concurrent.atomic.AtomicInteger;

import com.atm.dispenser.DispenserChain;
import com.atm.dispenser.NoteDispenser100;
import com.atm.dispenser.NoteDispenser20;
import com.atm.dispenser.NoteDispenser50;
import com.atm.state.ATMState;
import com.atm.state.IdleState;

public class ATM {

	private static ATM instance;
	private final CashDispenser cashDispenser;
	private final BankService bankService;
	private ATMState currentState;
	private Card currentCard;
	private static final AtomicInteger transactionCounter=new AtomicInteger(0);
	
	private ATM() {
		this.currentState=new IdleState();
		this.bankService=new BankService();
		
		DispenserChain c1=new NoteDispenser100(10);
		DispenserChain c2=new NoteDispenser50(20);
		DispenserChain c3=new NoteDispenser20(10);
		c1.setNextChain(c2);
		c2.setNextChain(c3);
		this.cashDispenser=new CashDispenser(c1);
	}
	
	public static synchronized ATM getInstance() {
		if(instance==null) {
			instance=new ATM();
		}
		return instance;
	}
	
	public void changeState(ATMState state) {
		this.currentState=state;
	}
	
	public void setCurrentCard(Card card) {
		this.currentCard=card;
	}
	
	public void insertCard(String cardNumber) {
		this.currentState.insertCard(instance, cardNumber);
	}
	
	public void enterPin(String pin) {
		this.currentState.enterPIN(instance, pin);
	}
	
	public void selectOperation(OperationType op,int... args) {
		currentState.selectOperation(instance, op, args);
	}
	
	public Card getCard(String cardNumber) {
		return this.bankService.getCard(cardNumber);
	}
	
	public boolean authenticate(String pin) {
		return this.bankService.authenticate(currentCard, pin);
	}
	
	public void checkBalance() {
		double balance=this.bankService.getBalance(currentCard);
		System.out.println("Your card has balance "+balance);
	}
	
	public Card getCurrentCard() {
		return this.currentCard;
	}
	
	public BankService getBankService() {
		return this.bankService;
	}
	
	public void depositeCach(int amount) {
		bankService.depositMoney(currentCard, amount);
	}
	
	public void withDrawCash(int amount) {
		if(!cashDispenser.canDispense(amount)) {
			throw new IllegalArgumentException("Insufficient cash available in ATM");
		}
		bankService.withdrawMoney(currentCard, amount);
		try {
			cashDispenser.dispneseCash(amount);
		}catch(Exception ex) {
			bankService.depositMoney(currentCard, amount);
		}
	}
}

