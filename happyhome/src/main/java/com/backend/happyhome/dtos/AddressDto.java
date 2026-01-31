package com.backend.happyhome.dtos;

import lombok.Data;

@Data
public class AddressDto {
	
	private Long addressId;
	private String homeNo;
    private String town;
    private String city;
    private String state;
    private String pincode;
}
