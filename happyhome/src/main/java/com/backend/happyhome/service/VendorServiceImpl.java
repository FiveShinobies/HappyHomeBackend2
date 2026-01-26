package com.backend.happyhome.service;

import org.springframework.stereotype.Service;

import com.backend.happyhome.custom_exceptions.OrderDoesNotExist;
import com.backend.happyhome.custom_exceptions.VendorDoesNotExistException;
import com.backend.happyhome.dtos.OrderDtoC;
import com.backend.happyhome.entities.Address;
import com.backend.happyhome.entities.Order;
import com.backend.happyhome.entities.Vendor;
import com.backend.happyhome.entities.VendorReview;
import com.backend.happyhome.entities.enums.Status;
import com.backend.happyhome.repository.OrderRepo;
import com.backend.happyhome.repository.VendorRepo;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class VendorServiceImpl implements VendorService{

	private final OrderRepo orderRepo;
	private final VendorRepo vendorRepo;
	
	VendorServiceImpl(OrderRepo orderRepo, VendorRepo vendorRepo){
		this.orderRepo = orderRepo;
		this.vendorRepo = vendorRepo;
	}
	
	@Override
	public boolean acceptRequest(Long oId, Long vId) throws OrderDoesNotExist{
		Order order = orderRepo.findById(oId).orElseThrow(()-> new OrderDoesNotExist());
		Vendor vendor = vendorRepo.findById(vId).orElseThrow(()-> new VendorDoesNotExistException());
		
		order.setMyVendor(vendor);
		order.setStatus(Status.ASSIGNED);
		orderRepo.save(order);
		return true;
	}

	@Override
	public VendorReview getReview(Long oId) {
		Order order = orderRepo.findById(oId).orElseThrow(()-> new OrderDoesNotExist());
		
		return order.getMyVendorReview();
	}

	@Override
	public OrderDtoC getOrderOfVendor(Long oId) {
		Order order = orderRepo.findById(oId).orElseThrow(()-> new OrderDoesNotExist());
		
		OrderDtoC orderDto = new OrderDtoC();
		orderDto.setAddress(order.getOrderAddress());
		orderDto.setMyVendor(order.getMyVendor());
		orderDto.setPrice(order.getOrderPrice());
		orderDto.setPriority(order.getPriority());
		orderDto.setTimeSlot(order.getTimeSlot());
		
		return orderDto;
	}

	@Override
	public Address getAddressOfOrder(Long oId) {
		Order order = orderRepo.findById(oId).orElseThrow(()-> new OrderDoesNotExist());
		
		Address address = order.getOrderAddress();
		
		return address;
	}

	
}
