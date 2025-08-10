package com.food.delivery.order;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.food.delivery.entity.Customer;
import com.food.delivery.entity.DeliveryAgent;
import com.food.delivery.entity.Restaurant;
import com.food.delivery.observer.OrderObserver;

public class Order {
	
	private final String id;
	private final Customer customer;
	private final Restaurant restaurant;
	private final List<OrderItem> items;
	private OrderStatus status;
	private DeliveryAgent deliveryAgent;
	private final List<OrderObserver> oberserves=new ArrayList<OrderObserver>();
	
	public Order(Customer customer, Restaurant restaurant, List<OrderItem> items) {
		super();
		this.id=UUID.randomUUID().toString();
		this.customer = customer;
		this.restaurant = restaurant;
		this.items = items;
		this.status=OrderStatus.PENDING;
		addObserver(customer);
		addObserver(restaurant);
	}

	private void addObserver(OrderObserver observer) {
        oberserves.add(observer);
	}
	
	private void notifyObserver() {
		oberserves.forEach(o->o.onUpdate(this));
	}
	
	public void setStatus(OrderStatus newStatus) {
		if(newStatus!=this.status) {
			this.status=newStatus;
			notifyObserver();
		}
	}
	
	public boolean cancel() {
		if(this.status==OrderStatus.PENDING) {
			setStatus(OrderStatus.CANCELLED);
			return true;
		}
		return false;
	}

	public void addDeliveryAgenet(DeliveryAgent agent) {
		this.deliveryAgent=agent;
		addObserver(agent);
		agent.setAvailable(false);
	}
	
	public String getId() {
		return id;
	}

	public Customer getCustomer() {
		return customer;
	}

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public List<OrderItem> getItems() {
		return items;
	}

	public OrderStatus getStatus() {
		return status;
	}

	public DeliveryAgent getDeliveryAgent() {
		return deliveryAgent;
	}

	public List<OrderObserver> getOberserves() {
		return oberserves;
	}
	
	

}
