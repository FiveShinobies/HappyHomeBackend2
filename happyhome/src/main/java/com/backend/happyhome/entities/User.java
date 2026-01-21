package com.backend.happyhome.entities;

import java.time.LocalDate;
import java.util.Set;

import com.backend.happyhome.entities.enums.UserRole;
import com.backend.happyhome.entities.enums.UserStatus;

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
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NonNull;

@Table(name = "users")
@Entity
@Data
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="user_id")
	private Long userId;
	@Column(name="first_name",length=100)
	@NonNull
	private String firstName;
	@Column(name="last_name",length=100)
	@NonNull
	private String lastName;
	@Column(nullable=false,unique=true)
	private String email;
	
	@NonNull
	private String password;
	
	@Column(length=10,nullable=false,unique=true)
	private String phone;
	
	@NonNull
	private LocalDate dob;
	
	@Enumerated(EnumType.STRING)
	@NonNull
	private UserStatus userStatus;
	
	@Enumerated(EnumType.STRING)
	@NonNull
	private UserRole role;

	@ManyToMany(cascade=CascadeType.ALL)
	@JoinTable(name = "user_language" , joinColumns = @JoinColumn(name="user_id"),inverseJoinColumns = @JoinColumn(name="lang_id"))
	private Set<Language> languages;

}
