package com.airline;

public class Payment {
	
	private final String paymentId;
	private final String paymentMehtod;
	private final double amount;
	private PaymentStatus paymentStatus;
	
	public Payment(String paymentId, String paymentMehtod, double amount) {
		this.paymentId = paymentId;
		this.paymentMehtod = paymentMehtod;
		this.amount = amount;
		this.paymentStatus = PaymentStatus.PENDING;
	}

	public String getPaymentId() {
		return paymentId;
	}

	public String getPaymentMehtod() {
		return paymentMehtod;
	}

	public double getAmount() {
		return amount;
	}

	public PaymentStatus getPaymentStatus() {
		return paymentStatus;
	}
	
	public void processPayment() {
		this.paymentStatus=PaymentStatus.COMPLETED;
	}
	
	

}
