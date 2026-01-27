package com.backend.happyhome.service;

import java.util.List;

import com.backend.happyhome.dtos.AdminOrderDetailsDTOE;
import com.backend.happyhome.dtos.ConsumerDtoC;
import com.backend.happyhome.entities.Consumer;
import com.backend.happyhome.entities.Order;
import com.backend.happyhome.dtos.ConsumerProfileDetailsDTOA;
import com.backend.happyhome.entities.Consumer;
import com.backend.happyhome.entities.Order;

public interface ConsumerService {

	List<ConsumerDtoC> getAllConsumers();
	ConsumerDtoC getConsumerDetailsById(Long cId);
	ConsumerDtoC editConsumerDetails(ConsumerDtoC dto,Long cId);
	List<AdminOrderDetailsDTOE> getAllOrdersOfConsumer(Long cId);
	Order getOrderOfConsumer(Long oId);
	
	ConsumerProfileDetailsDTOA getConsumerProfileDetailsById(Long cid);

	
}


