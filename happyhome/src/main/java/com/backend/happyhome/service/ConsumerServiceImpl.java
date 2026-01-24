package com.backend.happyhome.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.backend.happyhome.custom_exceptions.ConsumerNotFoundException;
import com.backend.happyhome.custom_exceptions.OrderDoesNotExist;
import com.backend.happyhome.dtos.ConsumerDtoC;
import com.backend.happyhome.entities.Address;
import com.backend.happyhome.entities.Consumer;
import com.backend.happyhome.entities.Order;
import com.backend.happyhome.entities.User;
import com.backend.happyhome.entities.enums.UserRole;
import com.backend.happyhome.repository.AddressRepo;
import com.backend.happyhome.repository.ConsumerRepo;
import com.backend.happyhome.repository.OrderRepo;
import com.backend.happyhome.repository.UserRepo;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class ConsumerServiceImpl implements ConsumerService {

	private final ConsumerRepo consumerRepo;
	private final UserRepo userRepo;
	private final AddressRepo addressRepo;
	private final OrderRepo orderRepo;
	@Override
	public List<Consumer> getAllConsumers() {
		return userRepo.findByRole(UserRole.CONSUMER);
	}

	@Override
	public ConsumerDtoC getConsumerDetailsById(Long cId) {
		Consumer c = consumerRepo.findById(cId).orElseThrow(()-> new ConsumerNotFoundException());
		User u = c.getMyUser();
		//map to dto
		ConsumerDtoC dto = new ConsumerDtoC();
		dto.setFirstName(u.getFirstName());
		dto.setLastName(u.getLastName());
		dto.setEmail(u.getEmail());
		dto.setPassword(u.getPassword());
		dto.setPhone(u.getPhone());
		dto.setDob(u.getDob());
		dto.setLanguages(u.getLanguages());
		dto.setUserStatus(u.getUserStatus());
		dto.setRewardPoints(c.getRewardPoints());
		dto.setAddress(addressRepo.findById(u.getUserId()).get());
		
		return dto;
	}

	@Override
	public ConsumerDtoC editConsumerDetails(ConsumerDtoC dto,Long cId) {
		Consumer c = consumerRepo.findById(cId).orElseThrow(()-> new ConsumerNotFoundException());
		
		User user = c.getMyUser();
		//map dto to entity
		user.setDob(dto.getDob());
		user.setEmail(dto.getEmail());
		user.setFirstName(dto.getFirstName());
		user.setLanguages(dto.getLanguages());
		user.setLastName(dto.getLastName());
		user.setPassword(dto.getPassword());
		user.setPhone(dto.getPhone());
		user.setUserStatus(dto.getUserStatus());
		
		userRepo.save(user);
		
		c.setRewardPoints(dto.getRewardPoints());
		consumerRepo.save(c);
		
		Address address = dto.getAddress();
		addressRepo.save(address);
		
		return dto;
	}

	@Override
	public List<Order> getAllOrdersOfConsumer(Long cId) {
		Consumer myConsumer = consumerRepo.findById(cId).orElseThrow(()-> new ConsumerNotFoundException());
		return orderRepo.findByMyConsumer(myConsumer);
	}
	
	public Order getOrderOfConsumer(Long oId) {
		return orderRepo.findById(oId).orElseThrow(()-> new OrderDoesNotExist());
	}

}
