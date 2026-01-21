package com.backend.happyhome.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "payment_upi")
@Data
public class PaymentUpi {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "upi_id")
	private Long upiId;
	
	@Column(name = "upi_address",nullable = false, unique = true, length = 200)
	private String upiAddress;
	
	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User myUser;
}
