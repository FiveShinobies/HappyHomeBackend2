package com.backend.happyhome.entities;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "signup_logs")
@Data
public class SignUpLog {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="signup_id")
	private Long signupId;
	@OneToOne
	@JoinColumn(name="user_id")
	private User user;
	@CreationTimestamp
	@Column(name="created_on")
	private LocalDateTime createdOn;
	@UpdateTimestamp
	@Column(name="updated_on")
	private LocalDateTime updatedOn;
}
