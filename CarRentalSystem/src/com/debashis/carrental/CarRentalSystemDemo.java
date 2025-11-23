package com.debashis.carrental;

import java.time.LocalDate;
import java.util.List;

public class CarRentalSystemDemo {
	
	public static void main(String[] args) {
		
		CarRentalSystem carRentalSystem=CarRentalSystem.getInstance();
		
		//Add cars to rental system
		carRentalSystem.addCar(new Car("Toyota", "Camry", 2022, "ABC123", 100.00));
		carRentalSystem.addCar(new Car("Honda","Civic",2021,"XYZ789",150.00));
		carRentalSystem.addCar(new Car("Ford","Mustang",2023,"DEF456",300.00));
		
		//create Customer
		Customer customer=new Customer("Debashis Nanda","debashis.nanda@gmail.com", "DLL123RT45");
		
		//Make reservation
		LocalDate startDate=LocalDate.now();
		LocalDate endDate=startDate.plusDays(3);
		List<Car> availableCars= carRentalSystem.searchCars("Toyota","Camry", startDate, endDate);
		if(!availableCars.isEmpty()) {
			Car seleatedCars=availableCars.getFirst();
			Reservation reservation=carRentalSystem.makeReservation(customer, seleatedCars, startDate, endDate);
			if(reservation!=null) {
				boolean paymentSataus=carRentalSystem.processPayment(reservation);
				if(paymentSataus) {
					System.out.println("Reservation suecessful  with rsservation id: "+reservation.getReservationId());
				}else {
					System.out.println("Payment failed. Reservation cancelled");
					carRentalSystem.cancelReservation(reservation.getReservationId());
				}
			}else {
				System.out.println("Selected car is not available for the given dates");
			}
		}else {
			System.out.println("No car is available for the given criteria");
		}
	}

}
