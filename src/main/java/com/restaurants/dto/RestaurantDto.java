package com.restaurants.dto;

import java.util.List;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
//The @Embeddable annotation in JPA (Java Persistence API) is used to 
//designate a class as an embeddable class. An embeddable class is a non-entity 
//class whose instances are embedded as part of the state of an entity. In simpler terms, 
//it allows you to create complex types that can be used as components within entity classes.
public class RestaurantDto {
	
	private String titale;
	
	private List<String> images;
	
	private String description;
	
	private Long id;

}
