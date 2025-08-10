package com.food.delivery;

import java.util.List;

import com.food.delivery.entity.Address;
import com.food.delivery.entity.Customer;
import com.food.delivery.entity.Menu;
import com.food.delivery.entity.MenuItem;
import com.food.delivery.entity.Restaurant;
import com.food.delivery.order.Order;
import com.food.delivery.order.OrderItem;
import com.food.delivery.order.OrderStatus;
import com.food.delivery.search.RestaurantSearchStrategy;
import com.food.delivery.search.SerachByCityStrategy;
import com.food.delivery.search.SerachByMenuKeywordStrategy;
import com.food.delivery.search.SerachByProximtyStrategy;
import com.food.delivery.strategy.NearestAvailableAgentStrategy;

public class FoodDeliveryServiceDemo {
	
	public static void main(String[] args) {
		
		//set up the system
		FoodDeliveryService service=FoodDeliveryService.getInstance();
		service.setAssignmentStrategy(new NearestAvailableAgentStrategy());
		
		//Define Address
		Address aliceAddress=new Address("123 Maple St", "SpringField","12345", 40.7128, -74.0060);
		Address pizaAddress=new Address("456 Oak Av", "SpringField","12345", 40.7128, -74.0070);
		Address burgerAddress=new Address("789 Pine Ln", "SpringField","12345", 40.7128, -74.0050);
		Address tacoAddress=new Address("101 Elm Ct", "SpringField","12345", 40.7128, -74.0160);
		
		//Register entities
		Customer aliceCustomer=service.registerCustomer("Alice","123-4567-890", aliceAddress);
		Restaurant pizzaPalce=service.registerRestaurant("Pizza Palace", pizaAddress);
		Restaurant burgerBarn=service.registerRestaurant("Burger Ban", burgerAddress);
		Restaurant tacoTown=service.registerRestaurant("Taco Town", tacoAddress);
		service.registerDEliveryAgent("Bob", "321-4567-880", new Address("1 B","SpringField","12345",40.7128, -74.0090));
		
		//set up menus
		pizzaPalce.addToMenu(new MenuItem("P001","Margherita Pizza", 12.99));
		pizzaPalce.addToMenu(new MenuItem("P002", "Veggie Pizza", 11.999));
		burgerBarn.addToMenu(new MenuItem("B001", "Classic Burger", 8.99));
		tacoTown.addToMenu(new MenuItem("T001","Crunchy Taco", 3.50));
		
		//demonstrate search Functionality
		System.out.println("\n---1. Seraching For Restaurants------");
		
		//search by city
		System.out.println("\n(A) Restaurnats in 'SpringField':");
	   List<RestaurantSearchStrategy> citySearch= List.of(new SerachByCityStrategy("SpringField"));
	   List<Restaurant> springFieldRestaurants= service.searchRestaurants(citySearch);
	  springFieldRestaurants.forEach(r->System.out.println("  -  "+r.getName()));	
	  
	  //search by restaurants near Alice
	  System.out.println("\n(B) Restaurants new Alice (within 0.01 distance units):");
	  List<RestaurantSearchStrategy> proximitySearch=List.of(new SerachByProximtyStrategy(aliceAddress, 0.01));
	  List<Restaurant> nearByRestaurants= service.searchRestaurants(proximitySearch);
	  nearByRestaurants.stream().forEach(r->System.out.printf(" -%s (Distance: %.4f)\n",r.getName(),aliceAddress.distanceTo(r.getAddress())));
	  
	  //search by restaurants
	  System.out.println("\n(C) Restaurants that serve 'Pizza':");
	  List<RestaurantSearchStrategy> menuSearch=List.of(new SerachByMenuKeywordStrategy("Pizza"));
	  List<Restaurant> pizzaRestaurants= service.searchRestaurants(menuSearch);
	  pizzaRestaurants.stream().forEach(p->System.out.println(" - "+p.getName()));
	  
	  //Find Restaurants near Alice that serves Taco
     System.out.println("\n(D)Taco joints near Alice");
    List<RestaurantSearchStrategy> combinedSearch= List.of(new SerachByProximtyStrategy(aliceAddress, 0.01),new SerachByMenuKeywordStrategy("Taco"));
	List<Restaurant> tacoJointAlice= service.searchRestaurants(combinedSearch);
	tacoJointAlice.stream().forEach(r->System.out.println(" - "+r.getName()));
	
	//Browsing a menu
	System.out.println("\n---2. Browsing a Menu-----");
	System.out.println("\n Menu for 'Pizza Palace':");
	Menu pizzaMenu=service.getRestaurantMenu(pizzaPalce.getId());
	pizzaMenu.getItems().values().forEach(item->System.out.printf(" - %s: $%.2f\n",item.getName(),item.getPrice()));
	
	//Alice place an order from searched restaurant
	System.out.println("\n--3. Placing a order-----");
	if(!pizzaRestaurants.isEmpty()) {
		Restaurant choosenRestaurant= pizzaRestaurants.get(0);
		MenuItem chooseItem=choosenRestaurant.getMenu().getItem("P001");
		
		System.out.printf("\nAlice is ordering '%s' from '%s'.\n",chooseItem.getName(),choosenRestaurant.getName());
		Order order= service.placeOrder(aliceCustomer.getId(),choosenRestaurant.getId(),List.of(new OrderItem(chooseItem, 1)));
		
		System.out.println("\nRestaurat starts preparing the order-----");
		service.updateOrderStatus(order.getId(),OrderStatus.PREPARING);
		
		System.out.println("\n Order is now ready for pickup-----");
		System.out.println("System will now find nearest available delivery agent-----");
		service.updateOrderStatus(order.getId(), OrderStatus.READY_FOR_PICKUP);
		
		System.out.println("\n Agents deliver the order------");
		service.updateOrderStatus(order.getId(),OrderStatus.DELIVERED);
	}
	
	

	
	}

}
