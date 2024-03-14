package com.restaurants.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.restaurants.model.Food;
import com.restaurants.model.User;
import com.restaurants.service.FoodService;
import com.restaurants.service.RestaurantService;
import com.restaurants.service.UserService;

@RestController
@RequestMapping("/api/food")
public class FoodController {

	@Autowired
	private FoodService foodService;
	@Autowired
	private UserService userService;
	@Autowired
	private RestaurantService restaurantService;
	
	@GetMapping("/search")
	public ResponseEntity<List<Food>> searchFood(@RequestParam String name,@RequestHeader("Authorization") String jwt) throws Exception{
		
		User user=userService.findUserByJwtToken(jwt);// we need to get the user using jwt token
		
		List<Food> foods = foodService.searchFood(name);
		
		return new ResponseEntity<>(foods,HttpStatus.CREATED);
		
	}
	
	@GetMapping("/restaurant/{restaurantId}")
	public ResponseEntity<List<Food>> getRestaurantFood(
			@RequestParam boolean vegitarian,
			@RequestParam boolean seasonal,
			@RequestParam boolean nonveg,
			@RequestParam(required = false) String food_category,
			@PathVariable Long restaurantId,
			@RequestHeader("Authorization") String jwt) throws Exception{
		
		User user=userService.findUserByJwtToken(jwt);// we need to get the user using jwt token
		
		List<Food> foods = foodService.getRestaurantFood(restaurantId, vegitarian, nonveg, seasonal, food_category);
		
		return new ResponseEntity<>(foods,HttpStatus.CREATED);
		
	}

}
