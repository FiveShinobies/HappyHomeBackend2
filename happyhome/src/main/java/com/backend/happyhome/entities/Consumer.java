package com.backend.happyhome.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Entity
@Data
public class Consumer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="consumer_id")
	private Long consumerId;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="user_id")
	private User myUser;
	@Column(name="reward_points",nullable=false)
	private int rewardPoints;
}
