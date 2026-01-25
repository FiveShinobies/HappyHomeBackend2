package com.backend.happyhome.service.consumer_service;

import org.springframework.stereotype.Service;

import com.backend.happyhome.entities.ConsumerTransaction;
import com.backend.happyhome.repository.consumer_repos.ConsumerTransactionRepo;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class ConsumerTransactionServiceImpl implements ConsumerTransactionService {

	private final ConsumerTransactionRepo ctRepo;
	
	@Override
	public ConsumerTransaction addTrasaction(ConsumerTransaction newTran) {
		
		return	ctRepo.save(newTran);
		
	}

}
