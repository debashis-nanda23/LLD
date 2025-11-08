package com.debashis.airline;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.debashis.airline.booking.Booking;
import com.debashis.airline.flight.Flight;
import com.debashis.airline.seat.Seat;
import com.debashis.airline.seat.SeatType;

public class AirlineManagementSystemDemo {
	
	public static void run() {
		
		AirlineManagementSystem airlineManagementSystem=new AirlineManagementSystem();
		
		//create passengers
		Passanger passenger1=airlineManagementSystem.addPassanger("John Doe","john.doe@gmail.com");
		Passanger passenger2=airlineManagementSystem.addPassanger("John Smith","john.smith@gmail.com");
		
		//create Aircrafts
		Aircraft aircraft1=airlineManagementSystem.addAircraft("A001", "Boeing 747",300);
		Aircraft aircraft2=airlineManagementSystem.addAircraft("A002", "Airbus 378",500);
		
		//create Flights
        Flight flight1=airlineManagementSystem.addFlight("New York","London",LocalDateTime.now().plusDays(1), LocalDateTime.now().plusHours(5),aircraft1.getTailNumber());
        Flight flight2=airlineManagementSystem.addFlight("Paris","Tokyo", LocalDateTime.now().plusDays(3),LocalDateTime.now().plusHours(6), aircraft2.getTailNumber());
        
        //search Flights
        List<Flight> flightList= airlineManagementSystem.searchFlight("New York","London",LocalDate.now().plusDays(1));
        flightList.stream().forEach(f->System.out.println("Flight: "+f.getFlightNumber()+"-"+f.getSource()+" - "+f.getDestination()));
		
        //Book a flight
       Booking booking= airlineManagementSystem.bookFlight(flight1.getFlightNumber(), passenger1.getId(), new Seat("6A", SeatType.ECONOMY), 70000);
       if(booking!=null) {
    	   System.out.println("Booking successfully with id "+booking.getId());
       }else {
    	   System.out.println("Booking Failed");
       }
	    
       
       //cancel a booking
       airlineManagementSystem.cancelBooking(booking.getId());
       System.out.println("Booking cancelled.");
	}
	
	public static void main(String[] args) {
		
		run();
	}

}
