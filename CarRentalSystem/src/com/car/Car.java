package com.car;

public class Car {
	
	private final String make;
	private final String model;
	private final int year;
	private final String licensePlate;
	private final double rentalPricePerDay;
	private boolean available;
	private final CarType carType;
	private final CarStatus carStatus;
	
	public Car(String make, String model, int year, String licensePlate, double rentalPricePerDay,CarType carType) {
		super();
		this.make = make;
		this.model = model;
		this.year = year;
		this.licensePlate = licensePlate;
		this.rentalPricePerDay = rentalPricePerDay;
		this.available=true;
		this.carType=carType;
		this.carStatus=CarStatus.AVAILABLE;
	}

	public boolean isAvailable() {
		return available;
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

	public double getRentalPricePerDay() {
		return rentalPricePerDay;
	}
	
	
	
	

}
