package com.car;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Reservation {

	private final String reservationId;
	private final Customer customer;
	private final Car car;
	private final LocalDate startDate;
	private final LocalDate endDate;
	private final double totalPrice;
	
	public Reservation(String reservationId, Customer customer, Car car, LocalDate startDate, LocalDate endDate) {
		super();
		this.reservationId = reservationId;
		this.customer = customer;
		this.car = car;
		this.startDate = startDate;
		this.endDate = endDate;
		this.totalPrice = calculatePrice();
	}

	public String getReservationId() {
		return reservationId;
	}

	public Customer getCustomer() {
		return customer;
	}

	public Car getCar() {
		return car;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public double getTotalPrice() {
		return totalPrice;
	}
	
	private double calculatePrice() {
		long days=ChronoUnit.DAYS.between(startDate, endDate)+1;
		return car.getRentalPricePerDay()*days;
	}
	
}
