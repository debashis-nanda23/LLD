package com.car;

import java.time.LocalDate;
import java.util.List;

public class CarRentalSystemDemo {
	
	public static void main(String[] args) {
		
		CarRentalSystem rentalSystem=CarRentalSystem.getInstance();
		
		//add cars
		rentalSystem.addCad(new Car("Toyota","Camry", 2000, "ABCX123", 2000, CarType.SUV));
		rentalSystem.addCad(new Car("Honda","Civic", 1000, "ABCX145", 1500, CarType.SEDAN));
		rentalSystem.addCad(new Car("Ford","Mustang", 3000, "ABCX167", 5000, CarType.HATCHBACK));
		
		//create customer
		Customer customer=new Customer("Debashis","deb@gmail.com", "DL1234");
		
		//make reservation
		LocalDate startDate=LocalDate.now();
		LocalDate endDate=LocalDate.now().plusDays(2);
		List<Car> searchedCars=rentalSystem.searchCars("Toyota","Camry", startDate, endDate);
		System.out.println("Car information is ");
		for(Car car:searchedCars) {
			System.out.println("Car Company Name "+car.getMake()+" of model "+car.getModel()+" is available with price per day "+car.getRentalPricePerDay());
		}
		if(!searchedCars.isEmpty()) {
		   Car selectedCar=searchedCars.getFirst();
		   Reservation reservation=rentalSystem.makeReservation(selectedCar, customer, startDate, endDate);
		   if(reservation!=null) {
			 boolean paymentStatus=rentalSystem.processPayment(reservation);
			 if(paymentStatus) {
				 System.out.println("Reservation done successful with reservation id "+reservation.getReservationId());
			 }else {
				 System.out.println("Payment Failed please try after some time");
				 rentalSystem.cancelReservation(reservation.getReservationId());
			 }
		   }else {
			   System.out.println("Reservation failed");
		   }
		}else {
			System.out.println("No car is available for rent");
		}
	}

}
