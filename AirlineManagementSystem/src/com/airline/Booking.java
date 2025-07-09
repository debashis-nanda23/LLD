package com.airline;

import java.util.UUID;

public class Booking {
	
	private final String id;
	private final Passanger passanger;
	private final Flight flight;
	private final Seat seat;
	private final double price;
	private BookingStatus bookingStatus;
	
	public Booking(Passanger passanger, Flight flight, Seat seat, double price) {
		
		this.id = UUID.randomUUID().toString();
		this.passanger = passanger;
		this.flight = flight;
		this.seat = seat;
		this.price = price;
		this.bookingStatus = BookingStatus.CONFIRMED;
	}
	
	public void cancel() {
		this.bookingStatus=BookingStatus.CANCELLED;
	}

	public String getId() {
		return id;
	}

	public Passanger getPassanger() {
		return passanger;
	}

	public Flight getFlight() {
		return flight;
	}

	public Seat getSeat() {
		return seat;
	}

	public double getPrice() {
		return price;
	}

	public BookingStatus getBookingStatus() {
		return bookingStatus;
	}
	
	
	
	

}
