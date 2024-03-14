package com.restaurants.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.restaurants.model.FoodCategory;
import com.restaurants.model.Restaurant;
import com.restaurants.repository.FoodCatrgoryRepository;
import com.restaurants.service.FoodCategoryService;
import com.restaurants.service.RestaurantService;

public class FoodCategoryServiceImpl implements FoodCategoryService{
	
	@Autowired
	private FoodCatrgoryRepository foodCatrgoryRepository;

	@Autowired
	private RestaurantService restaurantService;
	
	@Override
	public FoodCategory createCategory(String name, Long userId) throws Exception {
		Restaurant restaurant = restaurantService.getRestaurantByUserId(userId);
		FoodCategory category = new FoodCategory();
		category.setName(name);
		category.setRestaurant(restaurant);
		return foodCatrgoryRepository.save(category);
	}

	@Override
	public List<FoodCategory> findCategoryByRestaurantId(Long id) throws Exception {
		Restaurant restaurant = restaurantService.getRestaurantByUserId(id);
		return foodCatrgoryRepository.findByRestaurantId(restaurant.getId());
	}

	@Override
	public FoodCategory findCategoryById(Long id) throws Exception {
		Optional<FoodCategory> optional = foodCatrgoryRepository.findById(id);
		if(optional.isEmpty()) {
			throw new Exception("category not found..");
		}
		return optional.get();
	}

}
