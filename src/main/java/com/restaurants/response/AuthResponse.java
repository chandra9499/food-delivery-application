package com.restaurants.response;

import com.restaurants.enums.User_Role;

import lombok.Data;

@Data
public class AuthResponse {

	private String jwt;
	
	private String message;
	
	private User_Role role;
	
	
}
