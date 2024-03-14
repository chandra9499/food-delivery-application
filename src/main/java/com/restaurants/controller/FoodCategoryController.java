package com.restaurants.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.restaurants.model.FoodCategory;
import com.restaurants.model.User;
import com.restaurants.service.FoodCategoryService;
import com.restaurants.service.UserService;

@RestController
@RequestMapping("/api")
public class FoodCategoryController {
	
	@Autowired
	private FoodCategoryService categoryService;
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/admin/category")
	public ResponseEntity<FoodCategory> createCategory(
			@RequestBody FoodCategory category,
			@RequestHeader("Authorization") String jwt ) throws Exception{
		User user = userService.findUserByJwtToken(jwt);
		
		FoodCategory createCategory = categoryService.createCategory(category.getName(), user.getId());
		
		return new ResponseEntity<>(createCategory , HttpStatus.CREATED);
		
	}
	
	@GetMapping("/category/restaurant")
	public ResponseEntity<List<FoodCategory>> getReastaurantCategory(
			@RequestHeader("Authorization") String jwt ) throws Exception{
		User user = userService.findUserByJwtToken(jwt);
		
		List<FoodCategory> categories = categoryService.findCategoryByRestaurantId(user.getId());
		
		return new ResponseEntity<>(categories , HttpStatus.OK);
		
	}

}
