package com.food.delivery.strategy;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import com.food.delivery.entity.Address;
import com.food.delivery.entity.DeliveryAgent;
import com.food.delivery.order.Order;

public class NearestAvailableAgentStrategy implements DeliveryAssignmentStrategy{

	@Override
	public Optional<DeliveryAgent> findAgent(Order order, List<DeliveryAgent> agents) {
		Address restaurantAddress=order.getRestaurant().getAddress();
		Address customerAddress=order.getCustomer().getAddrss();
		
		return agents.stream().filter(DeliveryAgent::getAvailable)
				.min(Comparator.comparingDouble(agent->calculateTotalDistance(agent, restaurantAddress, customerAddress)));
	
	}
	
	private double calculateTotalDistance(DeliveryAgent agent,Address restaurantAddress,Address customerAddress) {
		double agentToRestaurantDistance=agent.getCurrentLocation().distanceTo(restaurantAddress);
		double restaurantToCustomerDistance=restaurantAddress.distanceTo(customerAddress);
		return agentToRestaurantDistance+restaurantToCustomerDistance;
	}

}
