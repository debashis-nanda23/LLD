package com.concert;

import java.util.List;

public class Booking {
	
	private final String id;
	private final User user;
	private final Concert concert;
	private final List<Seat> seats;
	private final double totalPrice;
	private BookingStatus bookingStatus;
	
	public Booking(String id, User user, Concert concert, List<Seat> seats) {
		super();
		this.id = id;
		this.user = user;
		this.concert = concert;
		this.seats = seats;
		this.totalPrice = calculateTotalPrice();
		this.bookingStatus=BookingStatus.PENDING;
	}

	public BookingStatus getBookingStatus() {
		return bookingStatus;
	}

	public void setBookingStatus(BookingStatus bookingStatus) {
		this.bookingStatus = bookingStatus;
	}

	public String getId() {
		return id;
	}

	public User getUser() {
		return user;
	}

	public Concert getConcert() {
		return concert;
	}

	public List<Seat> getSeats() {
		return seats;
	}

	public double getTotalPrice() {
		return totalPrice;
	}
	
	private double calculateTotalPrice() {
		return seats.stream().mapToDouble(Seat::getPrice).sum();
	}
	
	
	public void confirmBooking() {
		if(this.bookingStatus==BookingStatus.PENDING) {
			this.bookingStatus=BookingStatus.CONFIRMED;
			System.out.println("Booking is confirmed with id "+id);
		}
	}
	
	
	public void cancelBoooking() {
		if(this.bookingStatus==BookingStatus.CONFIRMED) {
			this.bookingStatus=BookingStatus.CANCELLED;
			this.seats.forEach(Seat::release);
			System.out.println("Booking is cancelled with id "+id);
		}
	}

}
