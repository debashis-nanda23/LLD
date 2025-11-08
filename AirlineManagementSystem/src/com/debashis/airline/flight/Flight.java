package com.debashis.airline.flight;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.debashis.airline.Aircraft;
import com.debashis.airline.seat.Seat;

public class Flight {
	
	private final String flightNumber;
	private final String source;
	private final String destination;
	private final LocalDateTime departureTime;
	private final LocalDateTime arrivalTime;
	private final FlightStatus flightStatus;
	private final Aircraft aircraft;
	private final Map<String,Seat> seats;
	private final List<Seat> availableSeats;
	
	public Flight(String source, String destination, LocalDateTime departureTime,
			LocalDateTime arrivalTime, Aircraft aircraft) {
		super();
		this.flightNumber = UUID.randomUUID().toString();
		this.source = source;
		this.destination = destination;
		this.departureTime = departureTime;
		this.arrivalTime = arrivalTime;
		this.flightStatus = FlightStatus.ON_TIME;
		this.aircraft = aircraft;
		this.seats = new HashMap<>();
		this.availableSeats = new ArrayList<>();
	}

	public String getFlightNumber() {
		return flightNumber;
	}

	public String getSource() {
		return source;
	}

	public String getDestination() {
		return destination;
	}

	public LocalDateTime getDepartureTime() {
		return departureTime;
	}

	public LocalDateTime getArrivalTime() {
		return arrivalTime;
	}

	public FlightStatus getFlightStatus() {
		return flightStatus;
	}

	public Aircraft getAircraft() {
		return aircraft;
	}

	public Map<String, Seat> getSeats() {
		return seats;
	}

	public List<Seat> getAvailableSeats() {
		return availableSeats;
	}
	
	public synchronized boolean isSeatAvailable(String seatNumber) {
	    Seat seat=seats.get(seatNumber);
	    return seat!=null && !seat.isBooked();
	}
	
	public synchronized void reserveSeat(String seatNumber) {
		Seat seat=seats.get(seatNumber);
		if(seat==null) {
			throw new IllegalArgumentException("Provided seat number already booked");
		}
		seat.reserve();
	}
	
	public void releaseSeat(String seatNumber) {
		Seat seat=seats.get(seatNumber);
		if(seat==null) {
			throw new IllegalArgumentException("Provided seat number not available");
		}
		seat.release();
	}
	
	
	
	

}
