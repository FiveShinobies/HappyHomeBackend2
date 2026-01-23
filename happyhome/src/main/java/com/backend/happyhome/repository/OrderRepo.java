package com.backend.happyhome.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.backend.happyhome.dtos.OrderDtoC;
import com.backend.happyhome.entities.Order;
import com.backend.happyhome.entities.enums.Status;
import com.backend.happyhome.entities.Order;

@Repository
public interface OrderRepo extends JpaRepository<Order, Long> {

	List<OrderDtoC> findByStatus(Status status);

	List<Order> findByMyConsumerConsumerId(Long cid);	
	
}
