package com.backend.happyhome.dtos;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import com.backend.happyhome.entities.*;

import lombok.Data;

@Data
public class VendorRegisterDtoC {

	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private String phone;
	private LocalDate dob;
	private Address address;
	private String aadhardNo;
	private String panNo;
	private int experience;
	private Set<HouseholdService> services = new HashSet<>();
}
