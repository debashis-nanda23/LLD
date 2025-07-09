package com.airline;

public class Aircraft {
	
	private final String tailNumber;
	private final String model;
	private final int seats;
	
	public String getTailNumber() {
		return tailNumber;
	}

	public String getModel() {
		return model;
	}

	public int getSeats() {
		return seats;
	}

	public Aircraft(String tailNumber, String model, int seats) {
		super();
		this.tailNumber = tailNumber;
		this.model = model;
		this.seats = seats;
	}
	
	

}
