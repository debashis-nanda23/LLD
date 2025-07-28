package com.concert;

import java.time.LocalDateTime;
import java.util.List;

public class Concert {
	
	private final String id;
	private final String artist;
	private final LocalDateTime dateTime;
	private final String venue;
	private final List<Seat> seats;
	
	public String getId() {
		return id;
	}

	public String getArtist() {
		return artist;
	}

	public LocalDateTime getDateTime() {
		return dateTime;
	}

	public String getVenue() {
		return venue;
	}

	public List<Seat> getSeats() {
		return seats;
	}

	public Concert(String id, String artist, LocalDateTime dateTime, String venue, List<Seat> seats) {
		super();
		this.id = id;
		this.artist = artist;
		this.dateTime = dateTime;
		this.venue = venue;
		this.seats = seats;
	}
	

}
