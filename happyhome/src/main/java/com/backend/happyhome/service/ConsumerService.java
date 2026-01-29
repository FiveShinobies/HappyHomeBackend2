package com.backend.happyhome.service;

import java.util.List;

import com.backend.happyhome.dtos.AddressDto;
import com.backend.happyhome.dtos.ConsumerDtoC;
import com.backend.happyhome.entities.Order;

public interface ConsumerService {

	List<ConsumerDtoC> getAllConsumers();
	ConsumerDtoC getConsumerDetailsById(Long cId);
	ConsumerDtoC editConsumerDetails(ConsumerDtoC dto,Long cId);
	List<Order> getAllOrdersOfConsumer(Long cId);
	Order getOrderOfConsumer(Long oId);

	boolean addAddress(Long cid , AddressDto ad);
	
}


