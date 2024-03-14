package com.restaurants.service;

import java.util.List;

import com.restaurants.model.Food;
import com.restaurants.model.FoodCategory;
import com.restaurants.model.Restaurant;
import com.restaurants.request.CreateFoodRequest;

public interface FoodService {

	public Food createFood(CreateFoodRequest request,FoodCategory category , Restaurant restaurant);
	
	public void deleteFood(Long foodId) throws Exception;
	
	public List<Food> getRestaurantFood(Long restaurantId , boolean isVagitarain , boolean isNonVeg,boolean isSeasonal,String foodCategory);
	
	public List<Food> searchFood(String keyword);
	
	public Food findByFoodID(Long foodId) throws Exception;
	
	public Food updateAvailibityStatus(Long foodId) throws Exception;
}
