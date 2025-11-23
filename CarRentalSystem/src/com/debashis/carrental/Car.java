package com.debashis.carrental;

public class Car {
	
	private final String make;
	private final String model;
	private final int year;
	private final String licensePlate;
	private final double renatlPricePerDay;
	private boolean available;
	
	public Car(String make, String model, int year, String licensePlate, double renatlPricePerDay) {
		super();
		this.make = make;
		this.model = model;
		this.year = year;
		this.licensePlate = licensePlate;
		this.renatlPricePerDay = renatlPricePerDay;
		this.available = true;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

	public String getMake() {
		return make;
	}

	public String getModel() {
		return model;
	}

	public int getYear() {
		return year;
	}

	public String getLicensePlate() {
		return licensePlate;
	}

	public double getRenatlPricePerDay() {
		return renatlPricePerDay;
	}

	public boolean isAvailable() {
		return available;
	}
	
	

}
