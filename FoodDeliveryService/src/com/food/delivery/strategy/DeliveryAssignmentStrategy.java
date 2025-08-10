package com.food.delivery.strategy;

import java.util.List;
import java.util.Optional;

import com.food.delivery.entity.DeliveryAgent;
import com.food.delivery.order.Order;

public interface DeliveryAssignmentStrategy {

	Optional<DeliveryAgent> findAgent(Order order,List<DeliveryAgent> agents);
}
