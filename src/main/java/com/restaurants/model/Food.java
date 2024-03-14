package com.restaurants.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "food")
public class Food {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String name;
	
	private String description;
	
	private Long price;
	
	@ManyToOne
	private FoodCategory foodCategory;
	
	@Column(length = 1000)
	@ElementCollection //Separate table for food images
	private List<String> images;
	
	private boolean available;
	
	@ManyToOne
	private Restaurant restaurant;
	
	private boolean vegetarian;
	
	private boolean Seasonal;
	
	@ManyToMany
	private List<IngredientsItem> ingredientsItems = new ArrayList<>();
	
	private Date creationDate;
	
}
