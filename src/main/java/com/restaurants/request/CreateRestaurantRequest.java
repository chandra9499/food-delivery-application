package com.restaurants.request;

import java.util.List;

import com.restaurants.dto.ContactInformation;
import com.restaurants.model.Address;

import lombok.Data;

@Data
public class CreateRestaurantRequest {

	private Long id;
	private String name;
	private String description;
	private String cuisineType;
	private Address address;
	private ContactInformation contactInformation;
	private String openHours;
	private List<String> images;
}
