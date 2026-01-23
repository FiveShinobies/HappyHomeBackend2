package com.backend.happyhome.entities;

import java.time.LocalDate;

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
@Table(name = "payment_card")
@Data
public class PaymentCard {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "card_id")
	private Long cardId;
	
	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User myUser;
	
	@Column(name = "card_no", nullable = false, unique = true, length = 20)
	private String cardNo;
	
	@Column(name = "expiry_date", nullable = false)
	private LocalDate expiryDate;
	
}
