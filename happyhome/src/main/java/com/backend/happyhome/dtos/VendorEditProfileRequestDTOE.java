package com.backend.happyhome.dtos;

import java.time.LocalDate;
import java.util.Set;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VendorEditProfileRequestDTOE {
	
	private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private LocalDate dob;

    private String homeNo;
    private String city;
    private String state;
    private String pincode;

    private Set<Long> serviceIds;  
    private Set<Long> languageIds;
    
}	
