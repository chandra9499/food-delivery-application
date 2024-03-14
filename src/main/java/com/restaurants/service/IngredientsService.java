package com.restaurants.service;

import java.util.List;

import com.restaurants.model.IngredientCategory;
import com.restaurants.model.IngredientsItem;
//we are handling ingredient item and ingredient category in one place but they have separate model hence they should have different repository
public interface IngredientsService {

	public IngredientCategory createIngredientCategory(String name , Long restaurantId) throws Exception;
	
	public IngredientCategory findIngredientCategoryById(Long id) throws Exception;
	
	public List<IngredientCategory> findIngredientCategoryByRestaurantId(Long id) throws Exception;
	
	public IngredientsItem createIngredientsItem(Long restaurantId , String ingredientName,Long categoryId) throws Exception;// to create the ingredient
	
	public List<IngredientsItem> findRestaurantsIngredients(Long restaurantId);// all the ingredients within the restaurant
	
	//to check the stock is present or not
	public IngredientsItem updateStock(Long id) throws Exception;
}
