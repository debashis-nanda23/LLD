package com.concert;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class ConcertTicketBookingSystem {
	
	private static ConcertTicketBookingSystem instance;
	private final Map<String,Concert> concerts;
	private final Map<String,Booking> bookings;
	private final Object lock=new Object();
	
	public static synchronized ConcertTicketBookingSystem getInstance() {
		if(instance==null) {
			instance=new ConcertTicketBookingSystem();
		}
		return instance;
	}
	
	private ConcertTicketBookingSystem() {
		concerts=new ConcurrentHashMap<String, Concert>();
		bookings=new ConcurrentHashMap<String, Booking>();
	}
	
	public void addConcert(Concert concert) {
		concerts.put(concert.getId(), concert);
	}
	
	public Concert getConcert(String concertId) {
		return concerts.get(concertId);
	}
	
	public List<Concert> getConcerts(String artist,String venue,LocalDateTime dateTime){
		return concerts.values().stream().filter(consert->consert.getArtist().equals(artist)
				                                   && consert.getVenue().equals(venue)
				                                   && consert.getDateTime().equals(dateTime))
		.collect(Collectors.toList());
	}
	
	public Booking bookTickets(Concert concert,User user,List<Seat> seats) {
		synchronized (lock) {
			for(Seat seat:seats) {
				if(seat.getSeatStatus()!=SeatStatus.AVAILABLE) {
					throw new SeatNotFoundException("Seat "+seat.getSeatNumber()+" is not available for booking");
				}
			}
			seats.forEach(Seat::book);
			
			//create booking
			String bookingId=generateBookingId();
			Booking booking=new Booking(bookingId, user, concert, seats);
			bookings.put(bookingId, booking);
			processPayment(booking);
			booking.confirmBooking();
			System.out.println("Booking "+booking.getId()+"-"+seats.size()+" seats booked");
			return booking;
			
		}
	}
	
	public void cancelBooking(String bookingId) {
		Booking booking=bookings.get(bookingId);
		if(booking!=null) {
			booking.cancelBoooking();
			bookings.remove(bookingId);
		}
	}

	private void processPayment(Booking booking) {
		System.out.println("Payment done for the booking");
		
	}

	private String generateBookingId() {
		return "BKG"+UUID.randomUUID();
	}

}
