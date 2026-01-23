package com.backend.happyhome.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.happyhome.entities.PaymentCard;
import com.backend.happyhome.entities.PaymentUpi;

public interface PaymentCardRepo extends JpaRepository<PaymentCard, Long> {

	List<PaymentCard> findByMyUserUserId(Long uid);
	
}
