package com.backend.happyhome.entities;

import java.time.LocalDateTime;
import java.util.Set;

import org.hibernate.annotations.CreationTimestamp;

import com.backend.happyhome.entities.enums.Priority;
import com.backend.happyhome.entities.enums.Status;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;


@Entity
@Table(name="orders")
@Data
public class Order {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="order_id")
	private Long orderId;
	
	@ManyToOne
	@JoinColumn(name="consumer_id",nullable = false)
	private Consumer myConsumer;
	
	@ManyToOne
	@JoinColumn(name="vendor_id",nullable = false)
	private Vendor myVendor;
	
	@ManyToOne
	@JoinColumn(name = "service_id",nullable = false)
	private HouseholdService myServices;
	
	//one to one mappings ------------------------------------------------------------------------------------------
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="consumer_transaction_id",nullable = false)
	private ConsumerTransaction myConsumerTransaction;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="admin_transaction_id",nullable = false)
	private AdminTransaction myAdminTransaction;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="consumer_review_id",nullable = false)
	private ConsumerReview myConsumerReview;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="vendor_review_id",nullable = false)
	private VendorReview myVendorReview;
	//-----------------------------------------------------------------------------------------------------------------
	
	
	@Column(name="order_date_time")
	@CreationTimestamp
	private LocalDateTime orderDateTime;

	@Column(name="order_price")
	private Double orderPrice;
	
	@Column(name="status")
	@Enumerated(EnumType.STRING)
	private Status status;

	@Column(name="time_slot")
	private LocalDateTime timeSlot;
	
	@Column(name="priority")
	@Enumerated(EnumType.STRING)
	private Priority priority;
	
	@OneToOne
	@JoinColumn(name="address_id", nullable = false)
	private Address orderAddress;
	
	@OneToOne
	@JoinColumn(name="order_address")
	private Address myAddress;
	
}
