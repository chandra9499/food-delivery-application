package com.restaurants.service;

import java.util.List;

import com.restaurants.dto.RestaurantDto;
import com.restaurants.model.Restaurant;
import com.restaurants.model.User;
import com.restaurants.request.CreateRestaurantRequest;

public interface RestaurantService {
	
	public Restaurant createRestaurant(CreateRestaurantRequest req , User user);
	
	public Restaurant updateRestaurant(Long restaurantId , CreateRestaurantRequest updatedRequest) throws Exception;
	
	public void deleteRestaurant(Long restaurantId) throws Exception;
	
	public List<Restaurant> getAllRestaurant();
	
	public List<Restaurant> searchRestaurant(String keyword);
	
	public Restaurant findRestaurantById(Long restaurantId) throws Exception;
	
	public Restaurant getRestaurantByUserId(Long userId) throws Exception;
	
	public RestaurantDto addToFavorites(Long restaurantId , User user) throws Exception;
	
	public Restaurant updateRestaurantStatus(Long restaurantId) throws Exception;

}
