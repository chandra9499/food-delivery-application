package com.restaurants.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.restaurants.dto.RestaurantDto;
import com.restaurants.enums.User_Role;

import jakarta.persistence.CascadeType;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String fullName;
	
	private String email;
	@JsonProperty(access = Access.WRITE_ONLY)//we can't show the password when we we request for fetching operation the password can't be shown
	private String password;
	
	private User_Role userRole=User_Role.ROLE_CUSTOMER;
	
	@JsonIgnore//when your featching the user details you don't want order list
	@OneToMany(cascade = CascadeType.ALL , mappedBy = "customer")
	private List<Order> order= new ArrayList<>();
	
	// Using @ElementCollection to represent a collection of RestaurantDto
	@ElementCollection
	private List<RestaurantDto> favorites = new ArrayList<>();
	
	
	@OneToMany(cascade = CascadeType.ALL)//when the user is deleted all the address of user is deleted
	private List<Address> addresses=new ArrayList<>();
	
	

}
