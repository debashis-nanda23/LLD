package com.debashis.concertTicket;

import java.time.LocalDateTime;
import java.util.List;

public class Concert {
	
	private final String id;
	private final String artists;
	private final String venue;
	private final LocalDateTime dateTime;
	private final List<Seat> seats;
	
	public Concert(String id, String artists, String venue, LocalDateTime dateTime, List<Seat> seats) {
		super();
		this.id = id;
		this.artists = artists;
		this.venue = venue;
		this.dateTime = dateTime;
		this.seats = seats;
	}

	public String getId() {
		return id;
	}

	public String getArtists() {
		return artists;
	}

	public String getVenue() {
		return venue;
	}

	public LocalDateTime getDateTime() {
		return dateTime;
	}

	public List<Seat> getSeats() {
		return seats;
	}
	
	
	
	

}
