package com.food.delivery;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.concurrent.ConcurrentHashMap;

import com.food.delivery.entity.Address;
import com.food.delivery.entity.Customer;
import com.food.delivery.entity.DeliveryAgent;
import com.food.delivery.entity.Menu;
import com.food.delivery.entity.Restaurant;
import com.food.delivery.order.Order;
import com.food.delivery.order.OrderItem;
import com.food.delivery.order.OrderStatus;
import com.food.delivery.search.RestaurantSearchStrategy;
import com.food.delivery.strategy.DeliveryAssignmentStrategy;

public class FoodDeliveryService {
	
	private static volatile FoodDeliveryService instance;
	private final Map<String,Customer> customers;
	private final Map<String,Restaurant> restaurants;
	private final Map<String,DeliveryAgent> deliveryAgents;
	private final Map<String,Order> orders;
	private DeliveryAssignmentStrategy assignementStrategy;
	
	private FoodDeliveryService() {
		this.customers=new ConcurrentHashMap<String, Customer>();
		this.restaurants=new ConcurrentHashMap<String, Restaurant>();
		this.deliveryAgents=new ConcurrentHashMap<String, DeliveryAgent>();
		this.orders=new ConcurrentHashMap<String, Order>();
	}
	
	public static FoodDeliveryService getInstance() {
		if(instance==null) {
			synchronized (FoodDeliveryService.class) {
				instance=new FoodDeliveryService();
			}
		}
		return instance;
	}
	
	public void setAssignmentStrategy(DeliveryAssignmentStrategy assignmentStrategy) {
		this.assignementStrategy=assignmentStrategy;
	}
	
	public Customer registerCustomer(String name,String phone,Address address) {
		Customer customer=new Customer(name, phone, address);
		this.customers.put(customer.getId(), customer);
		return customer;
	}
	
	
	public Restaurant registerRestaurant(String name,Address address) {
		Restaurant restaurant=new Restaurant(name, address);
		this.restaurants.put(restaurant.getId(), restaurant);
		return restaurant;
	}
	
	public DeliveryAgent registerDEliveryAgent(String name,String phone,Address initialLocation) {
		DeliveryAgent deliveryAgent=new DeliveryAgent(name, phone, initialLocation);
		this.deliveryAgents.put(deliveryAgent.getId(), deliveryAgent);
		return deliveryAgent;
	}
	
	public Order placeOrder(String customerId,String restaurantId,List<OrderItem> items) {
		
		Customer customer=this.customers.get(customerId);
		Restaurant restaurnat=this.restaurants.get(restaurantId);
		if(customer==null || restaurnat==null) {
			throw new NoSuchElementException("Customer or restaurnats not found");
		}
		
		Order order=new Order(customer, restaurnat, items);
		this.orders.put(order.getId(), order);
		customer.addOrderToHistory(order);
		order.setStatus(OrderStatus.PENDING);
		System.out.printf("Order %s placed by %s at %s \n",order.getId(),customer.getName(),restaurnat.getName());
		return order;
	}
	
	
	public void updateOrderStatus(String orderId,OrderStatus newStatus) {
		Order order=this.orders.get(orderId);
		if(order==null) {
			throw new NoSuchElementException("Orders not found");
		}
		order.setStatus(newStatus);
		if(newStatus==OrderStatus.READY_FOR_PICKUP) {
			assignDelivery(order);
		}
		
	}

	private void assignDelivery(Order order) {
		List<DeliveryAgent> availableAgents=new ArrayList<DeliveryAgent>(this.deliveryAgents.values());
		
		assignementStrategy.findAgent(order, availableAgents).ifPresentOrElse(
				agent->{
					order.addDeliveryAgenet(agent);
					System.out.printf("Agent %s assugned to the order %s,\n",agent.getName(),agent.getCurrentLocation().distanceTo(order.getRestaurant().getAddress()),order.getId());
					order.setStatus(OrderStatus.OUT_FOR_DELIVERY);
				},()->System.out.println("No Delivery Agent found for this order "+order.getId())
				
				);
		
	}
	
	public void cancelOrder(String orderId) {
		Order order=this.orders.get(orderId);
		if(order==null) {
			throw new NoSuchElementException("Order not found");
		}
		
		if(order.cancel()) {
			System.out.println("SUCCESS: order "+orderId+" has been successfully cancelled");
		}else {
			System.out.println("FAILED: Order "+orderId+" could not be cancelled, Its status is "+order.getStatus());
		}
		
	}
	
	public List<Restaurant> searchRestaurants(List<RestaurantSearchStrategy> starategies){
		
		List<Restaurant> results=new ArrayList<Restaurant>(restaurants.values());
		for(RestaurantSearchStrategy strategy:starategies) {
			results=strategy.filter(results);
		}
		return results;
	}
	
	public Menu getRestaurantMenu(String restaurantId) {
		Restaurant restaurant=restaurants.get(restaurantId);
		if(restaurant==null) {
			throw new NoSuchElementException("Restaurant with ID "+restaurantId+" not found.");
		}
		return restaurant.getMenu();
	}
	

}
