package com.backend.happyhome.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.happyhome.entities.Order;

public interface OrderRepo extends JpaRepository<Order, Long> {
	long countByMyVendorVendorId(Long vendorId);
	
	List<Order> findByMyVendorVendorId(Long vendorId);
}
