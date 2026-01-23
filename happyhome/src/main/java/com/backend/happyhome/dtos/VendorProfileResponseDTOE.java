package com.backend.happyhome.dtos;

import java.time.LocalDate;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VendorProfileResponseDTOE {

	    private String fullName;
	    private List<String> servicesProvided;
	    private List<String> languages;
	    private double rating;
	    private long totalServices;
	    private long totalReviews;

	    private String firstName;
	    private String lastName;
	    private String email;
	    private String phone;
	    private LocalDate dateOfBirth;

	    private String aadhaarNumber;
	    private String panNumber;
	    
	    private String imageBase64;
	}

