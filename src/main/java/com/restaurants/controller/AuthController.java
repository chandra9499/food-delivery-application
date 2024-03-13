package com.restaurants.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.restaurants.config.JwtProvider;
import com.restaurants.enums.User_Role;
import com.restaurants.model.Cart;
import com.restaurants.model.User;
import com.restaurants.repository.CartRepository;
import com.restaurants.repository.UserRepository;
import com.restaurants.request.LoginRequest;
import com.restaurants.response.AuthResponse;
import com.restaurants.securityservice.CustomerUserDetailsService;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private JwtProvider jwtProvider;
	@Autowired
	private CustomerUserDetailsService customerUserDetailsService;
	@Autowired
	private CartRepository cartRepository;
	
	
//	to create the user or during sign up 
	/*
	 * {
	 *  "fullName": "chandra",
	 *  "email": "chandra@gmail.com", 
	 *  "password":"chandra@123", 
	 * "userRole": "ROLE_RESTAURANT_OWNER" 
	 * }
	 */
	@PostMapping("/signup")
	public ResponseEntity<AuthResponse> createUserHandler(@RequestBody User user) throws Exception{

		User isEmailExist = userRepository.findByEmail(user.getEmail());//check if user is present
		if(isEmailExist!=null) {
			throw new Exception("email is already present with another account");
		}
		
		User createdUser = new User();
		createdUser.setEmail(user.getEmail());
		createdUser.setFullName(user.getFullName());
		createdUser.setUserRole(user.getUserRole());
		createdUser.setPassword(passwordEncoder.encode(user.getPassword()));//we are going to save the encoded password
		
		User savedUser = userRepository.save(createdUser);//saving the user in repository
		
//		we are creating a cart for the customer
		Cart cart = new Cart();
		cart.setCustomer(savedUser);
		cartRepository.save(cart);
//	    now crate the authentication and by using this we will generated token
		
		Authentication authentication = new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword());
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		String jwt = jwtProvider.genarateToken(authentication);// token is generated
		
		AuthResponse authResponse = new AuthResponse();
		authResponse.setJwt(jwt);
		authResponse.setMessage("registered success fully..");
		authResponse.setRole(savedUser.getUserRole());
		
		return new ResponseEntity<>(authResponse,HttpStatus.CREATED);
		
	}	
//	to crate login method
	
	/*
	 * {
	 * 
	 * "email": "chandra@gmail.com", 
	 * "password": "chandra@123"
	 * 
	 * }
	 */
	@PostMapping("/signin")
	public ResponseEntity<AuthResponse> signin(@RequestBody LoginRequest loginRequest) throws Exception{
		
		String username=loginRequest.getEmail();
		String password = loginRequest.getPassword();
		
		Authentication authentication = authenticate(username,password);//Verification of the user with the credentials
		
//		to get the user role
		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();//we are getting the authorities
		
		String role = authorities.isEmpty()?null:authorities.iterator().next().getAuthority();//if authority is empty give null other wise get the authority
		
		
		//generate the token
		String jwt = jwtProvider.genarateToken(authentication);// token is generated
		
		AuthResponse authResponse = new AuthResponse();
		authResponse.setJwt(jwt);
		authResponse.setMessage("login success fully..");
		authResponse.setRole(User_Role.valueOf(role));//converting string into user role
		
		return new ResponseEntity<>(authResponse,HttpStatus.OK);
		
		
	}
	
	private Authentication authenticate(String username,String password) throws Exception {
		UserDetails userDetails = customerUserDetailsService.loadUserByUsername(username);
		if(userDetails==null) {
			throw new Exception("invalid username..");
		}
		if(!passwordEncoder.matches(password, userDetails.getPassword())) {
			throw new Exception("invalid password...");
		}
		
		return new UsernamePasswordAuthenticationToken(userDetails, null , userDetails.getAuthorities());
		
	}
 }

	
