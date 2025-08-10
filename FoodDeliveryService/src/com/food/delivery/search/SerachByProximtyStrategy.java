package com.food.delivery.search;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.food.delivery.entity.Address;
import com.food.delivery.entity.Restaurant;

public class SerachByProximtyStrategy implements RestaurantSearchStrategy{

	private final Address userLocation;
	private final double maxDistance;
	
	
	public SerachByProximtyStrategy(Address userLocation, double maxDistance) {
		super();
		this.userLocation = userLocation;
		this.maxDistance = maxDistance;
	}


	@Override
	public List<Restaurant> filter(List<Restaurant> allRestaurants) {
		return allRestaurants.stream()
			.filter(r->userLocation.distanceTo(r.getAddress())>=maxDistance)
			.sorted(Comparator.comparingDouble(r->userLocation.distanceTo(r.getAddress())))
			.collect(Collectors.toList());
	}

}
