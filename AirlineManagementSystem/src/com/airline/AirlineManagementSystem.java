package com.airline;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AirlineManagementSystem {
	
	private final Map<String,Flight> flights;
	private final Map<String,Aircraft> aircrafts;
	private final Map<String,Passanger> passangers;
	private final FlightSearch flightSearch;
	private final PaymentProcessor paymentProcessor;
	private final BookingManager bookingManager;
	
	public AirlineManagementSystem() {
		flights=new HashMap<String, Flight>();
		aircrafts=new HashMap<String, Aircraft>();
		passangers=new HashMap<String, Passanger>();
		flightSearch=new FlightSearch();
		paymentProcessor=PaymentProcessor.getInstance();
		bookingManager=BookingManager.getInstance();
	}
	
	public Passanger addPassanger(String name,String email) {
		Passanger passanger=new Passanger(name, email);
		passangers.put(passanger.getId(),passanger);
		return passanger;
	}
	
	public Aircraft addAircraft(String tailNumber,String model,int seat) {
		Aircraft aircraft=new Aircraft(tailNumber, model, seat);
		aircrafts.put(tailNumber, aircraft);
		return aircraft;
	}
	
	public Flight addFlight(String source,String destination,LocalDateTime departureDateTime,LocalDateTime arrivalDateTime,String aircraftNumber) {
		Flight flight=new Flight(source, destination, departureDateTime, arrivalDateTime, aircrafts.get(aircraftNumber));
		flights.put(flight.getFlightNumber(), flight);
		flightSearch.addFlight(flight);
		return flight;
	}
	
	public List<Flight> searchFlight(String source,String destination,LocalDate localDate){
		return flightSearch.searchFlight(source, destination, localDate);
	}
	
	public Booking bookingFlight(String flightNumber,String passangerId,Seat seat,double price) {
		return bookingManager.createBooking(flights.get(flightNumber),passangers.get(passangerId), seat, price);
	}
	
	public void cancleBooking(String bookingNumber) {
		bookingManager.cancelBooking(bookingNumber);
	}
	
	public void processPayment(Payment payment) {
		paymentProcessor.processPayment(payment);
	}
	
	

}
