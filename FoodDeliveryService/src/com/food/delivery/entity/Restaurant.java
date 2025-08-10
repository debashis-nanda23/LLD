package com.food.delivery.entity;

import java.util.UUID;

import com.food.delivery.observer.OrderObserver;
import com.food.delivery.order.Order;

public class Restaurant implements OrderObserver {

	private final String id;
	private final String name;
	private final Address address;
	private final Menu menu;
	
	
	
	public Restaurant(String name, Address address) {
		super();
		this.id=UUID.randomUUID().toString();
		this.name = name;
		this.address = address;
		this.menu=new Menu();
	}

	


	public String getId() {
		return id;
	}




	public String getName() {
		return name;
	}




	public Address getAddress() {
		return address;
	}




	public Menu getMenu() {
		return menu;
	}


	
	public void addToMenu(MenuItem item) {
		this.menu.addItem(item);
	}


	@Override
	public void onUpdate(Order order) {
		System.out.printf("---Notification For Restaurant %s---\n",getName());
		System.out.printf(" Order %s is now %s \n",order.getId(),order.getStatus());
		System.out.println("=====================================");
		
	}

}
