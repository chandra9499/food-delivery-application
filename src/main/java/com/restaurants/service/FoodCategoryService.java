package com.restaurants.service;

import java.util.List;

import com.restaurants.model.FoodCategory;

public interface FoodCategoryService {

	public FoodCategory createCategory(String name , Long userId) throws Exception;//name of the category and to find the restaurant using user id
	
	public List<FoodCategory> findCategoryByRestaurantId(Long id) throws Exception;
	
	public FoodCategory findCategoryById(Long id) throws Exception;
	
}
