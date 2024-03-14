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
//i/p for restaurant creation
/*
 * { "name":"food", "description":"description of the resturent",
 * "cuisineType":"indian", "address":{ "streetAddress":"h no 712",
 * "city":"bengaluru", "stateProvince":"karanataka", "postalCode":"560098",
 * "country":"india" }, "contactInformation":{ "email":"food@gmail.com",
 * "mobile":"91-7022046465", "twitter":"twitter", "instagram":"food" },
 * "openHours":"MON - SUN 9:00 AM TO 9:00 PM", "images":[
 * "https://onlinejpgtools.com/images/examples-onlinejpgtools/mountain-scene.jpg"
 * ,
 * "https://onlinejpgtools.com/images/examples-onlinejpgtools/coffee-with-strawberries.jpg"
 * ] }
 */
//o/p of the restaurant creation
/*
 * { "id": 1, "owner": { "id": 1, "fullName": "chandra", "email":
 * "chandra@gmail.com", "userRole": "ROLE_RESTAURANT_OWNER", "favorites": [],
 * "addresses": [] }, "name": "food", "description":
 * "description of the resturent", "cuisineType": "indian", "address": { "id":
 * 1, "streetAddress": "h no 712", "city": "bengaluru", "stateProvince":
 * "karanataka", "postalCode": "560098", "country": "india", "orders": null },
 * "contactInformation": { "email": "food@gmail.com", "mobile": "91-7022046465",
 * "twitter": "twitter", "instagram": "food" }, "openingHours":
 * "MON - SUN 9:00 AM TO 9:00 PM", "orders": [], "images": [
 * "https://onlinejpgtools.com/images/examples-onlinejpgtools/mountain-scene.jpg",
 * "https://onlinejpgtools.com/images/examples-onlinejpgtools/coffee-with-strawberries.jpg"
 * ], "registrationDate": "2024-03-14T00:29:46.302582", "isOpen": null }
 */