package com.restaurants.serviceimpl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restaurants.dto.RestaurantDto;
import com.restaurants.model.Address;
import com.restaurants.model.Restaurant;
import com.restaurants.model.User;
import com.restaurants.repository.AddressRepository;
import com.restaurants.repository.RestaurantRepository;
import com.restaurants.repository.UserRepository;
import com.restaurants.request.CreateRestaurantRequest;
import com.restaurants.service.RestaurantService;

@Service
public class RestaurantServiceImpl implements RestaurantService{

	@Autowired
	private RestaurantRepository restaurantRepository;
	
	@Autowired
	private AddressRepository addressRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public Restaurant createRestaurant(CreateRestaurantRequest req, User user) {
		Address address = addressRepository.save(req.getAddress());
		
		Restaurant restaurant = new Restaurant();
		restaurant.setAddress(address);
		restaurant.setContactInformation(req.getContactInformation());
		restaurant.setCuisineType(req.getCuisineType());
		restaurant.setDescription(req.getDescription());
		restaurant.setImages(req.getImages());
		restaurant.setName(req.getName());
		restaurant.setOpeningHours(req.getOpenHours());
		restaurant.setRegistrationDate(LocalDateTime.now());
		restaurant.setOwner(user);
		
		return restaurantRepository.save(restaurant);
	}

	@Override
	public Restaurant updateRestaurant(Long restaurantId, CreateRestaurantRequest updatedRequest) throws Exception {
		Restaurant restaurant = findRestaurantById(restaurantId);
		// we can change all the fields
		if(restaurant.getCuisineType()!=null) {
			restaurant.setCuisineType(updatedRequest.getCuisineType());
		}
		if(restaurant.getDescription()!=null){
			restaurant.setDescription(updatedRequest.getDescription());
		}
		if(restaurant.getName()!=null) {
			restaurant.setDescription(updatedRequest.getDescription());
		}
		return restaurantRepository.save(restaurant);
	}

	@Override
	public void deleteRestaurant(Long restaurantId) throws Exception {
		Restaurant restaurant = findRestaurantById(restaurantId);// this method will throw the exception 
		restaurantRepository.delete(restaurant);
		
	}

	@Override
	public List<Restaurant> getAllRestaurant() {
		
		return restaurantRepository.findAll();
	}

	@Override
	public List<Restaurant> searchRestaurant(String keyword) {
		return restaurantRepository.findBySearchQuery(keyword);
	}

	@Override
	public Restaurant findRestaurantById(Long restaurantId) throws Exception {
		Optional<Restaurant> findById = restaurantRepository.findById(restaurantId);
		if(findById.isEmpty()) {
			throw new Exception("restaurant not present with : "+restaurantId);
		}
		return findById.get();
	}

	@Override
	public Restaurant getRestaurantByUserId(Long userId) throws Exception {
		Restaurant restaurant=restaurantRepository.findByOwnerId(userId);
		if(restaurant==null) {
			throw new Exception("restaurent not found with user id : "+userId);
		}
		return restaurant;
	}

	@Override
	public RestaurantDto addToFavorites(Long restaurantId, User user) throws Exception {
		Restaurant restaurant = findRestaurantById(restaurantId);
		RestaurantDto dto = new RestaurantDto();
		dto.setDescription(restaurant.getDescription());
		dto.setImages(restaurant.getImages());
		dto.setTitale(restaurant.getName());
		dto.setId(restaurantId);
//		if the restaurant is all ready in favorites add otherwise remove it
		/*
		 * if(user.getFavorites().contains(dto)) { user.getFavorites().remove(dto); }
		 * else { user.getFavorites().add(dto); }
		 */
		
		boolean isFavorited = false;
		List<RestaurantDto> favorites = user.getFavorites();
		for(RestaurantDto favorite : favorites) {
			if(favorite.getId().equals(restaurantId)) {
				isFavorited=true;
				break;
			}
		}
		if(isFavorited) {
			favorites.removeIf(favorite -> favorite.getId().equals(restaurantId));
		}
		else {
			favorites.add(dto);
		}
		userRepository.save(user);
		return dto;
	}

	@Override
	public Restaurant updateRestaurantStatus(Long restaurantId) throws Exception {
		Restaurant restaurant = findRestaurantById(restaurantId);
		restaurant.setOpen(!restaurant.isOpen());// we are negating the information
		return restaurantRepository.save(restaurant);
	}
	
}
