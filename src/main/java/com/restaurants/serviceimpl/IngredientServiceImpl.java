package com.restaurants.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restaurants.model.IngredientCategory;
import com.restaurants.model.IngredientsItem;
import com.restaurants.model.Restaurant;
import com.restaurants.repository.IngredientCategoryRepository;
import com.restaurants.repository.IngredientItemRepository;
import com.restaurants.service.IngredientsService;
import com.restaurants.service.RestaurantService;

@Service
public class IngredientServiceImpl implements IngredientsService{

	@Autowired
	private IngredientItemRepository ingredientItemRepository;
	@Autowired
	private IngredientCategoryRepository ingredientCategoryRepository;

	@Autowired
	private RestaurantService restaurantService;
	@Override
	public IngredientCategory createIngredientCategory(String name, Long restaurantId) throws Exception {
		//we will get the restaurant 
		Restaurant restaurant = restaurantService.findRestaurantById(restaurantId);
		// we are creating a ingredient category and and storing the restaurant and name of the category
		IngredientCategory category = new IngredientCategory();
		category.setRestaurant(restaurant);
		category.setName(name);
		
		return ingredientCategoryRepository.save(category);
	}

	@Override
	public IngredientCategory findIngredientCategoryById(Long id) throws Exception {
		Optional<IngredientCategory> optional = ingredientCategoryRepository.findById(id);
		if(optional.isEmpty()) {
			throw new Exception("ingredient category is not present by id :"+id);
		}
		return optional.get();
	}

	@Override
	public List<IngredientCategory> findIngredientCategoryByRestaurantId(Long id) throws Exception {
		//first check if the restaurant exist or not 
		restaurantService.findRestaurantById(id);
		// find IngredientCategory in that restaurant
		return ingredientCategoryRepository.findByRestaurantId(id);
	}

	@Override
	public IngredientsItem createIngredientsItem(Long restaurantId, String ingredientName, Long categoryId)
			throws Exception {
		// we found restaurant exist or not
		Restaurant restaurant = restaurantService.findRestaurantById(restaurantId);
		//found category exist or not
		IngredientCategory category = findIngredientCategoryById(categoryId);
		//we need to crate a ingredient item set the value
		IngredientsItem item = new IngredientsItem();
		item.setRestaurant(restaurant);
		item.setName(ingredientName);
		item.setCategory(category);
		//save it to the repository and add it to the ingredient category item list
		IngredientsItem ingredient = ingredientItemRepository.save(item);
		category.getIngredients().add(ingredient);
		return ingredient;
	}

	@Override
	public List<IngredientsItem> findRestaurantsIngredients(Long restaurantId) {
		// all the ingredient item in the restaurant
		List<IngredientsItem> allingredientitem = ingredientItemRepository.findByRestaurantId(restaurantId);
		return allingredientitem;
	}

	@Override
	public IngredientsItem updateStock(Long id) throws Exception {
		Optional<IngredientsItem> optional = ingredientItemRepository.findById(id);
		if(optional.isEmpty()) {
			throw new Exception("ingidents not found...");
		}
		IngredientsItem ingredientItem = optional.get();
		ingredientItem.setInStoke(!ingredientItem.isInStoke());
		return ingredientItemRepository.save(ingredientItem);
	}
}
