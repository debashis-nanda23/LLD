package com.food.delivery.entity;

import java.util.concurrent.atomic.AtomicBoolean;

import com.food.delivery.order.Order;

public class DeliveryAgent extends User {

	private Address currentLocation;
	private final AtomicBoolean isAvailable=new AtomicBoolean(true);
	
	public DeliveryAgent(String name, String phone,Address currentLocation) {
		super(name, phone);
		this.currentLocation=currentLocation;
	}
	
	public void setAvailable(boolean available) {
		this.isAvailable.set(available);
	}
	
	public boolean getAvailable() {
		return this.isAvailable.get();
	}
	
	

	public Address getCurrentLocation() {
		return currentLocation;
	}

	public void setCurrentLocation(Address currentLocation) {
		this.currentLocation = currentLocation;
	}

	@Override
	public void onUpdate(Order order) {
		System.out.printf("---Notification For Delivery Agenet %s---\n",getName());
		System.out.printf(" Order %s is now %s \n",order.getId(),order.getStatus());
		System.out.println("=====================================");
		
	}

}
