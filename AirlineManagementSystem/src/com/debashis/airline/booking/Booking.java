package com.debashis.airline.booking;

import java.util.UUID;

import com.debashis.airline.Passanger;
import com.debashis.airline.flight.Flight;
import com.debashis.airline.seat.Seat;

public class Booking {
	
	private final String id;
	private final Flight flight;
	private final Passanger passanger;
	private final Seat seat;
	private final double price;
	private BookingStatus bookingStatus;
	
	public Booking(Flight flight, Passanger passanger, Seat seat, double price) {
		super();
		this.id = UUID.randomUUID().toString();
		this.flight = flight;
		this.passanger = passanger;
		this.seat = seat;
		this.price = price;
		this.bookingStatus = BookingStatus.CONFIRMED;
	}
	
	public void cancel() {
		this.bookingStatus=BookingStatus.CANCELLED;
		seat.release();
	}

	public String getId() {
		return id;
	}
	
	
	
	
	

}
