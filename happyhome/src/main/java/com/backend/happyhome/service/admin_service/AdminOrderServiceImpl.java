package com.backend.happyhome.service.admin_service;

import java.util.List;

import com.backend.happyhome.custom_exceptions.OrderDoesNotExistException;
import com.backend.happyhome.entities.Order;
import com.backend.happyhome.repository.OrderRepo;

public class AdminOrderServiceImpl implements AdminOrderService {

	OrderRepo orderRepo;
	
	@Override
	public List<Order> getAllOrders() {
		return orderRepo.findAll();
	}

	@Override
	public Order getOrderDetailsById(Long oid) {
		return orderRepo.findById(oid).orElseThrow(() -> new OrderDoesNotExistException("OrderNotFound -- admin service"));
	}
	
	
	
}
