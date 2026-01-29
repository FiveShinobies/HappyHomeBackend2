package com.backend.happyhome.dtos;

import java.util.List;

import com.backend.happyhome.entities.Address;
import com.backend.happyhome.entities.Consumer;
import com.backend.happyhome.entities.Language;

import com.backend.happyhome.entities.UserImage;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//response dto for getConsumerDetailsById
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConsumerProfileDetailsDTOA {

	@NotNull
	Consumer consumer;
	
	List<Address> addresses;

	UserImage image;
	
	List<Language> languages;
	
}
