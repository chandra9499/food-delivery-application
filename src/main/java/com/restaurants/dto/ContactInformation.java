package com.restaurants.dto;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
//@Embeddable
public class ContactInformation {
	
	private String email;
	
	private String mobile;
	
	private String twitter;
	
	private String instagram;

}
