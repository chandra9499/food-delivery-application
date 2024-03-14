package com.restaurants.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.restaurants.model.Food;

public interface FoodRepository extends JpaRepository<Food, Long> {

	List<Food> findByRestaurentId(Long restaurantId);
	
	@Query("SELECT f FROM Food f WHERE f.name LIKE %:keyword% OR f.foodCategory LIKE %:keyword%")
	List<Food> searchFood(@Param("keyword") String keyword);
}
