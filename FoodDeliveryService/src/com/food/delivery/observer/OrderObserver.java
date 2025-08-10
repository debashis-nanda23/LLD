package com.food.delivery.observer;

import com.food.delivery.order.Order;

public interface OrderObserver {
	
	void onUpdate(Order order);

}
