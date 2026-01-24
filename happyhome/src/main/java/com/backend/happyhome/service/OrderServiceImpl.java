package com.backend.happyhome.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.backend.happyhome.custom_exceptions.CannotChangeTimeSlotException;
import com.backend.happyhome.custom_exceptions.OrderDoesNotExist;
import com.backend.happyhome.custom_exceptions.OrderDoesNotExistException;
import com.backend.happyhome.custom_exceptions.ReviewAlreadyExistsException;
import com.backend.happyhome.dtos.ConsumerReviewDTOA;
import com.backend.happyhome.dtos.OrderDtoC;
import com.backend.happyhome.dtos.PlaceOrderDTOA;
import com.backend.happyhome.entities.Address;
import com.backend.happyhome.entities.Consumer;
import com.backend.happyhome.entities.ConsumerReview;
import com.backend.happyhome.entities.HouseholdService;
import com.backend.happyhome.entities.Order;
import com.backend.happyhome.entities.enums.Status;
import com.backend.happyhome.repository.AddressRepo;
import com.backend.happyhome.repository.ConsumerRepo;
import com.backend.happyhome.repository.ConsumerReviewRepo;
import com.backend.happyhome.repository.HouseholdServiceRepo;
import com.backend.happyhome.repository.OrderRepo;
import com.backend.happyhome.repository.VendorRepo;

import lombok.RequiredArgsConstructor;


@Service
@Transactional
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{

	private final OrderRepo orderRepo;
	
	private final ConsumerReviewRepo crRepo;
	
	private final ConsumerRepo consumerRepo;
	
	private final VendorRepo vendorRepo;
	
	private final HouseholdServiceRepo serviceRepo;
	
	private final AddressRepo addressRepo; 
	
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
		
	@Override
	public List<Order> getOrdersByConsumerId(Long cid) {
		return orderRepo.findByMyConsumerConsumerId(cid);
	}

	@Override
	public Order getOrderDetailsById(Long oid) {
		return orderRepo.findById(oid).orElse(null);
	}

	
	@Override
	public Order changeTimeSlot( Long oid , LocalDateTime updatedTime) {
		
		
		Order o = orderRepo.findById(oid).orElseThrow();
		
		if(o.getStatus() != Status.UNASSIGNED) {
			throw new CannotChangeTimeSlotException("TIme Slot cannot be changed as order is already " + o.getStatus() );
		}
		
		o.setOrderDateTime(updatedTime);
		orderRepo.save(o);
		return o;
	}

	@Override
	public ConsumerReview addConsumerReviewForAnOrder(Long oid, ConsumerReviewDTOA cr) {
		
		if(orderRepo.findById(oid).orElse(null) == null ) {
			throw new OrderDoesNotExistException("Order Does not Exist!!");
		}
		
		if(crRepo.findByMyOrderOrderId(oid) != null) {
			throw new ReviewAlreadyExistsException("Review Already Exists!!");
		}
		 
		Order odr = orderRepo.findById(oid).orElseThrow();
		ConsumerReview cf = new ConsumerReview();
		cf.setDescription(cr.getDescription()); 
		cf.setRating(cr.getRating());
		cf.setMyOrder(odr);
		
		crRepo.save(cf);
		
		return cf;
	}

	@Override
	public Order addOrder(PlaceOrderDTOA reqOdr) {
		
		Order newOdr = new Order();
		
	
		Consumer c = consumerRepo.findById(reqOdr.getConsumerId()).orElseThrow();
		
		HouseholdService s = serviceRepo.findById(reqOdr.getServiceId()).orElseThrow();
		
		Address a = addressRepo.findById(reqOdr.getAddressId()).orElseThrow() ;
		
		newOdr.setMyConsumer(c);
		newOdr.setMyServices(s);
		newOdr.setMyAddress(a);
		newOdr.setOrderDateTime(reqOdr.getTimeSlot());
		newOdr.setOrderPrice(reqOdr.getOrderPrice());
		newOdr.setStatus(reqOdr.getStatus());
		newOdr.setPriority(reqOdr.getPriority());
		
		orderRepo.save(newOdr);
		
		return newOdr;
	}

}
