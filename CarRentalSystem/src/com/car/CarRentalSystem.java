package com.car;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import com.car.payment.CreditCardPaymentProcessor;
import com.car.payment.PaymentProcessor;

public class CarRentalSystem {
	
	private static CarRentalSystem instance;
	private final Map<String,Car> cars;
	private final Map<String,Reservation> reservations;
	private final PaymentProcessor paymentProcessor;
	
	
	private CarRentalSystem() {
		cars=new ConcurrentHashMap<String, Car>();
		reservations=new ConcurrentHashMap<String, Reservation>();
		paymentProcessor=new CreditCardPaymentProcessor();
	}
	
	public static synchronized CarRentalSystem getInstance() {
		if(instance==null) {
			instance=new CarRentalSystem();
		}
		return instance;
	}
	
	public void addCad(Car car) {
		cars.put(car.getLicensePlate(), car);
	}
	
	public void removeCar(String licensePlate) {
		cars.remove(licensePlate);
	}
	
	public List<Car> searchCars(String make,String model,LocalDate startDate,LocalDate endDate){
		List<Car> searchedCar=new ArrayList<Car>();
		for(Car car:cars.values()) {
			if(car.getMake().equalsIgnoreCase(make) && car.getModel().equalsIgnoreCase(model) && isCarAvailable(car, startDate, endDate)) {
				searchedCar.add(car);
			}
		}
		return searchedCar;
	}
	
	private boolean isCarAvailable(Car car,LocalDate startDate,LocalDate endDate) {
		for(Reservation reservation:reservations.values()) {
			if(startDate.isBefore(reservation.getStartDate()) && endDate.isAfter(reservation.getEndDate())) {
				return false;
			}
		}
		return true;
	}
	
	public synchronized Reservation makeReservation(Car car,Customer customer,LocalDate startDate,LocalDate endDate) {
		if(isCarAvailable(car, startDate, endDate)) {
			String reservationId=UUID.randomUUID().toString();
			Reservation reservation=new Reservation(reservationId, customer, car, startDate, endDate);
			reservations.put(reservationId, reservation);
			car.setAvailable(false);
			return reservation;
		}
		return null;
	}
	
	public synchronized void cancelReservation(String reservationId) {
		Reservation reservation=reservations.get(reservationId);
		if(reservation!=null) {
			reservation.getCar().setAvailable(true);
		}
	}
		
	public boolean processPayment(Reservation reservation) {
		return paymentProcessor.processPayment(reservation.getTotalPrice());
	}
	

}
