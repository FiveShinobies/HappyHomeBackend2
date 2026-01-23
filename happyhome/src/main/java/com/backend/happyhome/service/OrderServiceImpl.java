package com.backend.happyhome.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.backend.happyhome.custom_exceptions.OrderDoesNotExist;
import com.backend.happyhome.dtos.OrderDtoC;
import com.backend.happyhome.entities.Address;
import com.backend.happyhome.entities.Consumer;
import com.backend.happyhome.entities.Order;
import com.backend.happyhome.entities.enums.Status;
import com.backend.happyhome.repository.OrderRepo;


@Service
@Transactional
public class OrderServiceImpl implements OrderService{

	private final OrderRepo orderRepo;
	
	public OrderServiceImpl(OrderRepo orderRepo) {
		// TODO Auto-generated constructor stub
		this.orderRepo = orderRepo;
	}
	
	@Override
	public List<OrderDtoC> getIncomingOrderRequest() {
		return orderRepo.findByStatus(Status.UNASSIGNED);
	}

	@Override
	public List<OrderDtoC> getOngoingOrders() {
		return orderRepo.findByStatus(Status.INPROGRESS);
	}
	
	public Address getAddress(Long oId) {
		Order order = orderRepo.findById(oId).orElseThrow(()->new OrderDoesNotExist());
		
		return order.getOrderAddress();
	}

	@Override
	public Consumer getConsumer(Long oId) {
		Order order = orderRepo.findById(oId).orElseThrow(()->new OrderDoesNotExist());
		
		return order.getMyConsumer();
	}

	@Override
	public boolean updateStatusToInProgress(Long oId) {
		Order order = orderRepo.findById(oId).orElseThrow(()->new OrderDoesNotExist());
		
		order.setStatus(Status.INPROGRESS);
		
		return true;
	}

	@Override
	public boolean updateStatusToCompleted(Long oId) {
		Order order = orderRepo.findById(oId).orElseThrow(()->new OrderDoesNotExist());
		
		order.setStatus(Status.COMPLETED);
		
		return true;
	}
	
}
