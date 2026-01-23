package com.backend.happyhome.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.happyhome.entities.Order;

public interface OrderRepo extends JpaRepository<Order, Long> {

	List<Order> findByMyConsumerConsumerId(Long cid);	
	
}
