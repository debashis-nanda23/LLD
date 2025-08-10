package com.food.delivery.entity;

import java.util.ArrayList;
import java.util.List;

import com.food.delivery.order.Order;

public class Customer extends User {

	private Address addrss;
	private final List<Order> orderHistory=new ArrayList<>();
	
	public Customer(String name, String phone,Address address) {
		super(name, phone);
		this.addrss=address;
	}

	public void addOrderToHistory(Order order) {
		this.orderHistory.add(order);
	}
	
	public Address getAddrss() {
		return addrss;
	}
	
	public List<Order> getOrderHistory() {
		return orderHistory;
	}

	@Override
	public void onUpdate(Order order) {
		System.out.printf("---Notification For Customer %s---\n",getName());
		System.out.printf(" Order %s is now %s \n",order.getId(),order.getStatus());
		System.out.println("=====================================");
		
		
	}

}
