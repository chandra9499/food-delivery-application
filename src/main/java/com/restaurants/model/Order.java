package com.restaurants.model;



import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orders")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@ManyToOne
	private User customer;
	
	@JsonIgnore//if i am fetching order details we don't need restaurant details
	@ManyToOne
	private Restaurant restaurant;
	
	
	private Long totalAmout;
	
	private String orderStatus;
	
	private Date createdAt;
	
	@ManyToOne//
	private Address deliveryAddress;
	
	
	@OneToMany
	private List<OrderItem> items;
	
//	private Payment payment;
	
	private Integer totalItem;
	
	private Integer trotalPrice;
}
