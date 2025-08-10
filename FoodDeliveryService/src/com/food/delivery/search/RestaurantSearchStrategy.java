package com.food.delivery.search;

import java.util.List;

import com.food.delivery.entity.Restaurant;

public interface RestaurantSearchStrategy {
	
	List<Restaurant> filter(List<Restaurant> allRestaurants);

}
