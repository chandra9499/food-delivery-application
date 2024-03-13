package com.restaurants.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.restaurants.model.Cart;

public interface CartRepository extends JpaRepository<Cart, Long>{

}
