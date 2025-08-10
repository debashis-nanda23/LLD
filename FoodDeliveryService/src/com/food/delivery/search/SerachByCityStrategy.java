package com.food.delivery.search;

import java.util.List;
import java.util.stream.Collectors;

import com.food.delivery.entity.Restaurant;

public class SerachByCityStrategy implements RestaurantSearchStrategy {
	
	private final String city;
	
	

	public SerachByCityStrategy(String city) {
		super();
		this.city = city;
	}



	@Override
	public List<Restaurant> filter(List<Restaurant> allRestaurants) {
		return allRestaurants.stream().filter(r->r.getAddress().getCity().equalsIgnoreCase(city)).collect(Collectors.toList());
	}

}
