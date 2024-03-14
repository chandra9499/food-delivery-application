package com.restaurants.request;

import java.util.List;

import com.restaurants.model.FoodCategory;
import com.restaurants.model.IngredientsItem;

import lombok.Data;

@Data
public class CreateFoodRequest {

	private String name;
	
	private String description;
	
	private Long price;
	
	private FoodCategory category;
	
	private List<String> images;
	
	private Long restaurantId;
	
	private boolean vagitarian;
	private boolean seasional;
	
	private List<IngredientsItem> ingredients;
}
