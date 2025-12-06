package com.debashis.concertTicket;

import java.util.List;

public class Booking {
	
	private final String id;
	private final Concert concert;
	private final User user;
	private final List<Seat> seats;
	private final double totalPrice;
	private BookingStatus status;
	
	public Booking(String id, Concert concert, User user, List<Seat> seats) {
		super();
		this.id = id;
		this.concert = concert;
		this.user = user;
		this.seats = seats;
		this.totalPrice=calculateTotalPrice();
		this.status=BookingStatus.PENDING;
	}

	private double calculateTotalPrice() {
		return this.seats.stream().mapToDouble(Seat::getPrice).sum();
	}

	public String getId() {
		return id;
	}

	public Concert getConcert() {
		return concert;
	}

	public User getUser() {
		return user;
	}

	public List<Seat> getSeats() {
		return seats;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public BookingStatus getStatus() {
		return status;
	}
	
	
	public synchronized void confirmBooking() {
		if(status==BookingStatus.PENDING) {
			this.status=BookingStatus.CONFIRMED;
		}
		System.out.println("Booking is confirmed.");
	}
	
	public void cancelBooking() {
		if(this.status==BookingStatus.CONFIRMED) {
			this.status=BookingStatus.CANCELLED;
			seats.forEach(Seat::release);
			System.out.println("Booing cancelled");
		}
	}
	
	
	

}
