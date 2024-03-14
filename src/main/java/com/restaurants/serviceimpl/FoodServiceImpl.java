package com.restaurants.serviceimpl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restaurants.model.Food;
import com.restaurants.model.FoodCategory;
import com.restaurants.model.Restaurant;
import com.restaurants.repository.FoodRepository;
import com.restaurants.request.CreateFoodRequest;
import com.restaurants.service.FoodService;

@Service
public class FoodServiceImpl implements FoodService {

	@Autowired
	private FoodRepository foodRepository;
	@Override
	public Food createFood(CreateFoodRequest request, FoodCategory category, Restaurant restaurant) {
		Food food = new Food();
		food.setFoodCategory(category);
		food.setRestaurant(restaurant);
		food.setDescription(request.getDescription());
		food.setImages(request.getImages());
		food.setName(request.getName());
		food.setPrice(request.getPrice());
		food.setIngredientsItems(request.getIngredients());
		food.setSeasonal(request.isSeasional());
		food.setVegetarian(request.isVagitarian());
		
		Food savedfood = foodRepository.save(food);
		restaurant.getFoods().add(savedfood);
		
		return savedfood;
	}

	@Override
	public void deleteFood(Long foodId) throws Exception {
		Food food = findByFoodID(foodId);
		food.setRestaurant(null);// we are removing the food from the restaurant
		foodRepository.save(food);

	}

	@Override
	public List<Food> getRestaurantFood(Long restaurantId, boolean isVegitarain, boolean isNonVeg, boolean isSeasonal,
			String foodCategory) {
		List<Food> foods = foodRepository.findByRestaurentId(restaurantId);// all the food in the restaurant
		
		if(isVegitarain) {
			foods = filterByVegitarian(foods,isVegitarain);
		}
		if(isNonVeg) {
			foods=filterByNonVeg(foods,isNonVeg);
		}
		if(isSeasonal) {
			foods= filterBySeasonal(foods,isSeasonal);
		}
		if(foodCategory!=null && !foodCategory.equals("")) {//if it's not empty and null apply filter
			foods = filterBYCategory(foods,foodCategory);
		}
		return null;
	}

	private List<Food> filterBYCategory(List<Food> foods, String foodCategory) {
		//if we want to apply filter we can go for stream
		return foods.stream().filter(food ->{
			if(food.getFoodCategory()!=null) {
				return food.getFoodCategory().getName().equals(foodCategory);
			}
			return false;
		}).collect(Collectors.toList());
	}

	private List<Food> filterBySeasonal(List<Food> foods, boolean isSeasonal) {
		// TODO Auto-generated method stub
		return foods.stream().filter(food -> food.isSeasonal()).collect(Collectors.toList());
	}

	private List<Food> filterByNonVeg(List<Food> foods, boolean isNonVeg) {
		// we are iterating through food and finding which food is non vegetarian and add to a collection
		return foods.stream().filter(food -> food.isVegetarian()==false).collect(Collectors.toList());
	}

	private List<Food> filterByVegitarian(List<Food> foods, boolean isVegitarain) {
		//we are iterating through food and finding which food is vegetarian and add to a collection
		return foods.stream().filter(food -> food.isVegetarian()==isVegitarain).collect(Collectors.toList());
	}

	@Override
	public List<Food> searchFood(String keyword) {
		
		return foodRepository.searchFood(keyword);
	}

	@Override
	public Food findByFoodID(Long foodId) throws Exception {
		Optional<Food> findById = foodRepository.findById(foodId);
		if(findById.isEmpty()) {
			throw new Exception("food not exist with id : "+foodId);
		}
		return findById.get();
	}

	@Override
	public Food updateAvailibityStatus(Long foodId) throws Exception {
		
		Food food = findByFoodID(foodId);
		food.setAvailable(!food.isAvailable());
		
		return foodRepository.save(food);
	}

}
