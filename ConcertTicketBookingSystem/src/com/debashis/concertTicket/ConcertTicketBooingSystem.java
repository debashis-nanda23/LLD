package com.debashis.concertTicket;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class ConcertTicketBooingSystem {
	
	private static ConcertTicketBooingSystem INSTANCE;
	private final Map<String,Booking> bookings;
	private final Map<String,Concert> concerts;
	private final Object lock=new Object();
	
	private ConcertTicketBooingSystem() {
		this.bookings=new ConcurrentHashMap<>();
		this.concerts=new ConcurrentHashMap<>();
	}
	
	public static synchronized ConcertTicketBooingSystem getInstance() {
		if(INSTANCE==null) {
			INSTANCE=new ConcertTicketBooingSystem();
		}
		return INSTANCE;
	}
	
	public void addConcert(Concert concert) {
		this.concerts.put(concert.getId(), concert);
	}
	
	public Concert getConcert(String concertId) {
		return concerts.get(concertId);
	}
	
	public List<Concert> searchConcert(String artist,String venue,LocalDateTime dateTime){
		return this.concerts.values().stream().
		                       filter(c->c.getArtists().equals(artist) && c.getVenue().equals(venue) && c.getDateTime().equals(dateTime))
		                       .collect(Collectors.toList());
	}

	public Booking bookTickets(User user,Concert concert,List<Seat> seats) {
		synchronized (lock) {
			for(Seat seat:seats) {
				if(seat.getStatus()!=SeatStatus.AVAILABLE) {
					throw new SeatNotFoundException("Seat "+seat.getSeatNumber()+" is not available.");
				}
			}
			seats.forEach(Seat::book);
			
			//create Booking
			String bookingId="BKG-"+UUID.randomUUID().toString();
			Booking booking=new Booking(bookingId, concert, user, seats);
			bookings.put(bookingId, booking);
			
			processPayment();
			
			booking.confirmBooking();
			System.out.println("Booking: "+booking.getId()+" - "+booking.getSeats().size()+" seats booked");
			return booking;
			
		}
	}
	
	public void cancelBooking(String bookingId) {
		Booking booking=bookings.get(bookingId);
		if(booking!=null) {
			booking.cancelBooking();
			bookings.remove(bookingId);
		}
	}

	private void processPayment() {
		System.out.println("Payment done successfull.");
		
	}
}
