package com.backend.happyhome.repository;

import java.util.Optional;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.happyhome.entities.PaymentUpi;

public interface PaymentUpiRepo extends JpaRepository<PaymentUpi, Long> {
//	Optional<PaymentUpi> findByMyUserUserId(Long userId);

	List<PaymentUpi> findByMyUserUserId(Long uid);
	
}
