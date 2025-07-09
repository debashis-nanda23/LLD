package com.airline;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class Main {
	
	public static void main(String[] args) {
		
		AirlineManagementSystem airlineManagementSystem=new AirlineManagementSystem();
		
		//create Passengers
		Passanger passenger1=airlineManagementSystem.addPassanger("Debashis Nanda", "debashis.nanda@gmail.com");
		Passanger passenger2=airlineManagementSystem.addPassanger("Sonali Dash", "sonali.dash@gmail.com");
		
		//create aircrafts
		Aircraft aircraft1=airlineManagementSystem.addAircraft("A001", "Boeing 747",300);
		Aircraft aircraft2=airlineManagementSystem.addAircraft("A002", "Airbus A380", 200);
		
		//create Flights
		Flight flight1=airlineManagementSystem.addFlight("Indore","Mumbai", LocalDateTime.now().plusDays(1),LocalDateTime.now().plusDays(1).plusHours(2),aircraft1.getTailNumber());
		Flight flight2=airlineManagementSystem.addFlight("Bhubanswar", "Indore",LocalDateTime.now().plusDays(2), LocalDateTime.now().plusDays(2).plusHours(3), aircraft2.getTailNumber());
		
		//search Flight
		List<Flight> flights=airlineManagementSystem.searchFlight("Indore", "Mumbai", LocalDate.now().plusDays(1));
		System.out.println("Search Flight Details are");
		for(Flight flight:flights) {
			System.out.println("Flight with number :"+flight.getFlightNumber()+" from "+flight.getSource()+" to "+flight.getDestination());
		}
		
		//book a flight
		Booking booking=airlineManagementSystem.bookingFlight(flight1.getFlightNumber(), passenger1.getId(), new Seat("25A", SeatType.ECONOMY), 5000);
		if(booking!=null) {
			System.out.println("Booking succesful with bookig id is :"+booking.getId());
		}else {
			System.out.println("Booking got Failed");
		}
		
		//cancel booking
		airlineManagementSystem.cancleBooking(booking.getId());
		System.out.println("Booking cancelled");
		
	}

}
