package com.debashis.airline;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.debashis.airline.booking.Booking;
import com.debashis.airline.booking.BookingManager;
import com.debashis.airline.flight.Flight;
import com.debashis.airline.flight.FlightSearch;
import com.debashis.airline.payment.Payment;
import com.debashis.airline.payment.PaymentProcessor;
import com.debashis.airline.seat.Seat;

public class AirlineManagementSystem {
	
	
	private final Map<String,Aircraft> aircrafts;
	private final Map<String,Flight> flights;
	private final Map<String,Passanger> passangers;
	private final BookingManager bookingManager;
	private final PaymentProcessor paymentProcessor;
	private final FlightSearch flightSearch;
	
	public AirlineManagementSystem() {
		aircrafts=new HashMap<>();
		flights=new HashMap<>();
		passangers=new HashMap<>();
		bookingManager=BookingManager.getInstance();
		paymentProcessor=PaymentProcessor.getInstance();
		flightSearch=new FlightSearch();
	}
	
	public Passanger addPassanger(String name,String email) {
		Passanger passanger=new Passanger(name, email);
		this.passangers.put(passanger.getId(), passanger);
		return passanger;
	}
	
	public Aircraft addAircraft(String tailNumber,String model,int totalSeats) {
		Aircraft aircraft=new Aircraft(tailNumber, model, totalSeats);
		this.aircrafts.put(aircraft.getTailNumber(), aircraft);
		return aircraft;
	}
	
	public Flight addFlight(String source, String destination, LocalDateTime departureTime,
			LocalDateTime arrivalTime, String tailNumber) {
		Flight flight=new Flight(source, destination, departureTime, arrivalTime, this.aircrafts.get(tailNumber));
		this.flights.put(flight.getFlightNumber(), flight);
		this.flightSearch.addFlight(flight);
		return flight;
	}
	
	public List<Flight> searchFlight(String source,String destination,LocalDate localDate){
		return this.flightSearch.searchFlights(source, destination, localDate);
	}
	
	public Booking bookFlight(String flightNumber,String passangerId,Seat seat,double price) {
		return this.bookingManager.createBooking(this.flights.get(flightNumber), this.passangers.get(passangerId), seat, price);
	}
	
	public void cancelBooking(String bookingNumber) {
		this.bookingManager.cancelBooking(bookingNumber);
	}

	public void processPayment(Payment payment) {
		this.paymentProcessor.processPayment(payment);
	}
	
	 

}
