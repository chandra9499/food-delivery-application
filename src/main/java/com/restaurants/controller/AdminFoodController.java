package com.restaurants.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.restaurants.model.Food;
import com.restaurants.model.Restaurant;
import com.restaurants.model.User;
import com.restaurants.request.CreateFoodRequest;
import com.restaurants.response.MessageResponse;
import com.restaurants.service.FoodService;
import com.restaurants.service.RestaurantService;
import com.restaurants.service.UserService;

@RestController
@RequestMapping("/api/admin/food")
public class AdminFoodController {
	@Autowired
	private FoodService foodService;
	@Autowired
	private UserService userService;
	@Autowired
	private RestaurantService restaurantService;
	
	@PostMapping()
	public ResponseEntity<Food> createFood(@RequestBody CreateFoodRequest request,@RequestHeader("Authorization") String jwt) throws Exception{
		User user=userService.findUserByJwtToken(jwt);// we need to get the user using jwt token
		
		Restaurant restaurant = restaurantService.findRestaurantById(request.getRestaurantId());
		
		Food food = foodService.createFood(request, request.getCategory(), restaurant);
		
		return new ResponseEntity<>(food,HttpStatus.CREATED);
		
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<MessageResponse> deleteFood(@PathVariable Long id,@RequestHeader("Authorization") String jwt) throws Exception{
		User user=userService.findUserByJwtToken(jwt);// we need to get the user using jwt token
		
		foodService.deleteFood(id);
		
		MessageResponse response = new MessageResponse();
		response.setMessage("food deleted success fully..");
		
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
	
	@PutMapping("{id}")
	public ResponseEntity<Food> updateFoodAvailability(@PathVariable Long id,@RequestHeader("Authorization") String jwt) throws Exception{
		User user=userService.findUserByJwtToken(jwt);// we need to get the user using jwt token
		
		Food food = foodService.updateAvailibityStatus(id);
		
		return new ResponseEntity<>(food,HttpStatus.OK);
		
	}

}
