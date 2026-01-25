package com.backend.happyhome.dtos;

import java.text.DecimalFormat;

import com.backend.happyhome.entities.enums.Category;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class CreateServiceRequestDTOB {

	private String serviceName;
	private String shortDesc;
	private String longDesc;
	private Double price;
	private Category category;
}
