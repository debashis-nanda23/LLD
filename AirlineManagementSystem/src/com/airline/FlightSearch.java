package com.airline;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FlightSearch {
	
	private final List<Flight> flights;

	public FlightSearch() {
		this.flights = new ArrayList<>();
	}
	
	public void addFlight(Flight flight) {
		this.flights.add(flight);
	}
	
	public List<Flight> searchFlight(String source,String destination,LocalDate localDate){
		return this.flights.stream().filter(f->f.getSource().equalsIgnoreCase(source) &&
				                               f.getDestination().equalsIgnoreCase(destination) &&
				                               f.getDepartureTime().toLocalDate().equals(localDate)).collect(Collectors.toList());
				  
				                            
	}
	
	

}
