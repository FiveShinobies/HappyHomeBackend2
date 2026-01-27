package com.backend.happyhome.dtos.consumer_dto;

import java.util.List;

import com.backend.happyhome.dtos.ConsumerAddressDetailsDTOB;
import com.backend.happyhome.dtos.ConsumerPaymentDetailsDTOB;
import com.backend.happyhome.entities.Address;
import com.backend.happyhome.entities.Consumer;
import com.backend.happyhome.entities.HouseholdService;
import com.backend.happyhome.entities.PaymentCard;
import com.backend.happyhome.entities.PaymentUpi;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class ConsumerDetailsForBookingDTOB {

	private ConsumerDetailsDTOB consumer;
	
	private List<ConsumerAddressDetailsDTOB> addresses;
	
	private HouseholdServiceDtoA service;
	
}
