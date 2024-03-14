package com.restaurants.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.restaurants.model.IngredientCategory;

@Repository
public interface IngredientCategoryRepository extends JpaRepository<IngredientCategory, Long>{

	public List<IngredientCategory> findByRestaurantId(Long id);
}
