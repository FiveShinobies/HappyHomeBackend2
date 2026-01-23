package com.backend.happyhome.service;

import java.util.List;

import com.backend.happyhome.dtos.OrderDtoC;
import com.backend.happyhome.entities.Address;
import com.backend.happyhome.entities.Consumer;

public interface OrderService {

	List<OrderDtoC> getIncomingOrderRequest();
	List<OrderDtoC> getOngoingOrders();
	Address getAddress(Long oID);
	Consumer getConsumer(Long oId);
	boolean updateStatusToInProgress(Long oId);
	boolean updateStatusToCompleted(Long oId);
}
