package com.restaurants.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.restaurants.model.Address;

public interface AddressRepository extends JpaRepository<Address, Long>{

}
