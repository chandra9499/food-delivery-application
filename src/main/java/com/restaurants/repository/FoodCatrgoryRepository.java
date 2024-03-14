package com.restaurants.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.restaurants.model.FoodCategory;

@Repository
public interface FoodCatrgoryRepository extends JpaRepository<FoodCategory, Long>{

	public List<FoodCategory> findByRestaurantId(Long id);
	
}
