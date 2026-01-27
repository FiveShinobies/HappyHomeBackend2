package com.backend.happyhome.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VendorFeedbackRequestDTOE {
	
	private Long orderId;
	
	
	private int rating;
	private String comments;

}
