package com.backend.happyhome.service.admin_service;

import java.util.List;

import com.backend.happyhome.entities.Order;

public interface AdminOrderService {

	List<Order> getAllOrders();
	
	Order getOrderDetailsById(Long oid);
	
}

//	getAllOrders
//	getOrderDetailsById
//	viewOrderFeedBacks