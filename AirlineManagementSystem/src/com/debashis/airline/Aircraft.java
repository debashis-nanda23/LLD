package com.debashis.airline;

public class Aircraft {
	
	private final String tailNumber;
	private final String model;
	private final int totalSeats;
	
	public Aircraft(String tailNumber, String model, int totalSeats) {
		super();
		this.tailNumber = tailNumber;
		this.model = model;
		this.totalSeats = totalSeats;
	}

	public String getTailNumber() {
		return tailNumber;
	}
	
	
	
	

}
