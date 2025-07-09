package com.airline;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class BookingManager {
	
	private static BookingManager instance;
	private final Map<String,Booking> bookings;
	private final Object lock=new Object();
	
	private BookingManager() {
		this.bookings = new HashMap<String, Booking>();
	}
	
	public static synchronized BookingManager getInstance() {
		if(instance==null) {
			instance=new BookingManager();
		}
		return instance;
	}

	public Booking createBooking(Flight flight,Passanger passanger,Seat seat,double price) {
		String bookingNumber=UUID.randomUUID().toString();
		Booking booking=new Booking(passanger, flight, seat, price);
		synchronized (lock) {
             bookings.put(bookingNumber, booking);			
		}
		return booking;
	}
	
	public void cancelBooking(String bookingNUmber) {
		Booking booking=bookings.get(bookingNUmber);
		if(booking!=null) {
			synchronized (lock) {
				booking.cancel();
				
			}
		}
	}
}
