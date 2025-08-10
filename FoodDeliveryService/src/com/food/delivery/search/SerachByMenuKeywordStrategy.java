package com.food.delivery.search;

import java.util.List;
import java.util.stream.Collectors;

import com.food.delivery.entity.Restaurant;

public class SerachByMenuKeywordStrategy implements RestaurantSearchStrategy {

	private final String keyword;
	
	
	public SerachByMenuKeywordStrategy(String keyword) {
		super();
		this.keyword = keyword;
	}


	@Override
	public List<Restaurant> filter(List<Restaurant> allRestaurants) {
         return allRestaurants.stream().
        		 filter(r->r.getMenu().getItems().values().stream().anyMatch(item->item.getName().toLowerCase().contains(keyword.toLowerCase())))
        		 .collect(Collectors.toList());
	}

}
