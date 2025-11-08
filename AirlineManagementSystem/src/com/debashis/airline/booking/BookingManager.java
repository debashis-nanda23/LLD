package com.debashis.airline.booking;

import java.util.HashMap;
import java.util.Map;

import com.debashis.airline.Passanger;
import com.debashis.airline.flight.Flight;
import com.debashis.airline.seat.Seat;

public class BookingManager {

	private static BookingManager instance;
	private final Map<String,Booking> bookings;
	private final Object lock=new Object();
	
	
	public static synchronized BookingManager getInstance() {
		if(instance==null) {
			instance=new BookingManager();
		}
		return instance;
	}
	
	public BookingManager() {
		this.bookings=new HashMap<>();
	}
	
	public Booking createBooking(Flight flight,Passanger passanger,Seat seat,double price) {
		Booking booking=new Booking(flight, passanger, seat, price);
		synchronized (lock) {
			bookings.put(booking.getId(), booking);
		}
		return booking;
	}
	
	public void cancelBooking(String bookingNumber) {
		Booking booking=bookings.get(bookingNumber);
		if(booking==null) {
			throw new IllegalArgumentException("Invalid Booking NUmber");
		}
		synchronized (lock) {
			booking.cancel();
		}
		
	}
}
