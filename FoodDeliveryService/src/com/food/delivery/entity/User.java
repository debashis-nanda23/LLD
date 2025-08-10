package com.food.delivery.entity;

import java.util.UUID;

import com.food.delivery.observer.OrderObserver;


public abstract class User implements OrderObserver{

	private final String id;
	private String name;
	private String phone;
	
	public User(String name, String phone) {
		super();
		this.id=UUID.randomUUID().toString();
		this.name = name;
		this.phone = phone;
	}

	public String getName() {
		return name;
	}

	public String getPhone() {
		return phone;
	}


	public String getId() {
		return id;
	}
	
	
	
	
	

}
