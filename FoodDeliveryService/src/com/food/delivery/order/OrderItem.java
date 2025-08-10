package com.food.delivery.order;

import com.food.delivery.entity.MenuItem;

public class OrderItem {
	
	private final MenuItem menuItem;
	private final int quantity;
	
	public MenuItem getMenuItem() {
		return menuItem;
	}

	public int getQuantity() {
		return quantity;
	}

	public OrderItem(MenuItem menuItem, int quantity) {
		super();
		this.menuItem = menuItem;
		this.quantity = quantity;
	}
	
	

}
