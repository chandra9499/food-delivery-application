package com.restaurants.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.restaurants.model.IngredientCategory;
import com.restaurants.model.IngredientsItem;
import com.restaurants.request.IngredientCategoryRequest;
import com.restaurants.request.IngredientsRequest;
import com.restaurants.service.IngredientsService;

@RestController
@RequestMapping("/api/admin/ingredients")
public class IngredientController {
	
	@Autowired
	private IngredientsService ingredientsService;
	
	
	@PostMapping("/category")
	public ResponseEntity<IngredientCategory> createIngredientCategory(
			@RequestBody IngredientCategoryRequest request) throws Exception{
		// we are going to create a item category inside restaurant by the given name
		IngredientCategory item = ingredientsService.createIngredientCategory(request.getName(), request.getRestaurantId());
		
		return new ResponseEntity<>(item,HttpStatus.CREATED);
		
	}
	
	@PostMapping("")
	public ResponseEntity<IngredientsItem> createIngredientItem(
			@RequestBody IngredientsRequest request) throws Exception{
		// we are going to create a items for the category inside restaurant by the given name
		IngredientsItem item = ingredientsService.createIngredientsItem(request.getRestaurantId(), request.getName(), request.getCategoryId());
		
		return new ResponseEntity<>(item,HttpStatus.CREATED);
		
	}

	@PutMapping("/{id}/stock")
	public ResponseEntity<IngredientsItem> updateIngredientsStock(
			@PathVariable Long id
			) throws Exception{
		IngredientsItem updateStock = ingredientsService.updateStock(id);
		
		return new ResponseEntity<>(updateStock,HttpStatus.OK);
		
	}
	
	@GetMapping("/restaurant/{id}")
	public ResponseEntity<List<IngredientsItem>> getRestaurantIngredients(
			@PathVariable Long id) throws Exception{
		// all the items in the restaurant 
		List<IngredientsItem> items = ingredientsService.findRestaurantsIngredients(id);
		
		return new ResponseEntity<>(items,HttpStatus.CREATED);
		
	}
	@GetMapping("/restaurant/{id}/category")
	public ResponseEntity<List<IngredientCategory>> getRestaurantIngredientCategory(
			@PathVariable Long id
			) throws Exception{
		// all the ingredient category in the restaurant 
		List<IngredientCategory> items = ingredientsService.findIngredientCategoryByRestaurantId(id);
		
		return new ResponseEntity<>(items,HttpStatus.CREATED);
		
	}
	
}
