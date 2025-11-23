package com.debashis.carrental;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class CarRentalSystem {
	
	private static CarRentalSystem instance=new CarRentalSystem();
	private final Map<String,Car> cars;
	private final Map<String,Reservation> reservations;
	private final PaymentProcessor paymentProcessor;
	
	public CarRentalSystem() {
		this.cars = new ConcurrentHashMap<>();
		this.reservations = new ConcurrentHashMap<>();
		this.paymentProcessor = new CrditCardPaymentProcessor();
	}
	
	public static CarRentalSystem getInstance() {
		return instance;
	}
	
	public void addCar(Car car) {
		this.cars.put(car.getLicensePlate(), car);
	}
	
	public void removeCar(String licensePlate) {
		this.cars.remove(licensePlate);
	}
	
	public List<Car> searchCars(String make,String model,LocalDate startDate,LocalDate endDate){
		List<Car> availableCars=new ArrayList<>();
		for(Car car:cars.values()) {
			if(car.getMake().equalsIgnoreCase(make) && car.getModel().equalsIgnoreCase(model) && car.isAvailable()) {
				if(isCarAvailable(car, startDate, endDate)) { 
				   availableCars.add(car);
				}
			}
		}
		return availableCars;
	}
	

	public boolean isCarAvailable(Car car,LocalDate startDate,LocalDate endDate) {
		for(Reservation reservation:reservations.values()) {
			if(reservation.getCar().equals(car) && startDate.isBefore(reservation.getEndDate()) && endDate.isAfter(reservation.getStartDate())) {
				return false;
			}
		}
		return true;
	}
	
	public synchronized Reservation makeReservation(Customer customer,Car car,LocalDate startDate,LocalDate endDate) {
		if(isCarAvailable(car, startDate, endDate)) {
			String reservationId=generateReservationId();
			Reservation reservation=new Reservation(reservationId, customer, car, startDate, endDate);
			this.reservations.put(reservationId, reservation);
			car.setAvailable(false);
			return reservation;
		}
		return null;
	}

	private String generateReservationId() {
		return "RES"+UUID.randomUUID().toString().toUpperCase().substring(0,8);
	}
	
	public synchronized void cancelReservation(String reservationId) {
		Reservation reservation=reservations.remove(reservationId);
		if(reservation!=null) {
		   reservation.getCar().setAvailable(true);
		}
	}
	
	public boolean processPayment(Reservation reservation) {
		return paymentProcessor.processPayment(reservation.getTotalPrice());
		}
}
