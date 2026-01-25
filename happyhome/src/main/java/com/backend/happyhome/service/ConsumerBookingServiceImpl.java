package com.backend.happyhome.service;



import java.util.List;

import org.springframework.stereotype.Service;

import com.backend.happyhome.custom_exceptions.UserNotPresentException;
import com.backend.happyhome.dtos.ConsumerAddressDetailsDTOB;
import com.backend.happyhome.dtos.consumer_dto.ConsumerDetailsDTOB;
import com.backend.happyhome.dtos.consumer_dto.ConsumerDetailsForBookingDTOB;
import com.backend.happyhome.entities.Address;
import com.backend.happyhome.entities.Consumer;
import com.backend.happyhome.entities.PaymentCard;
import com.backend.happyhome.entities.PaymentUpi;
import com.backend.happyhome.entities.User;
import com.backend.happyhome.repository.AddressRepo;
import com.backend.happyhome.repository.ConsumerRepo;
import com.backend.happyhome.repository.PaymentCardRepo;
import com.backend.happyhome.repository.PaymentUpiRepo;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class ConsumerBookingServiceImpl implements ConsumerBookingService {
	
	private final ConsumerRepo consumerRepo;
	private final AddressRepo addressRepo;

	
	@Override
	public ConsumerDetailsForBookingDTOB getConsumerDetailsForBooking(Long cid) {
		
		ConsumerDetailsForBookingDTOB consumerDetailsforBooking = new ConsumerDetailsForBookingDTOB();
		ConsumerDetailsDTOB consumerDetails = new ConsumerDetailsDTOB();
		
		//Getting the consumer with consumer id
		Consumer consumer =  consumerRepo.findById(cid)
				.orElseThrow(()->new UserNotPresentException());
		
		//Getting the user
		User user =  consumer.getMyUser();
		
		//Getting the userId from myUser 
		Long userId = consumer.getMyUser().getUserId();
		
		
//		//Getting address of the user
		List<ConsumerAddressDetailsDTOB> addresses = addressRepo.findByMyUserUserId(userId)
				.stream()
				.map(address -> new ConsumerAddressDetailsDTOB(
						address.getAddressId(),
						address.getHomeNo(),
						address.getTown(),
						address.getCity(),
						address.getState(),
						address.getPincode()))
				.toList();
		
		//setting Consumer Details
		consumerDetails.setCid(cid);
		consumerDetails.setName(user.getFirstName().concat(" ").concat(user.getLastName()));
		consumerDetails.setPhone(user.getPhone());
		
		//setting Consumer details with addresses
		consumerDetailsforBooking.setConsumer(consumerDetails);
		consumerDetailsforBooking.setAddresses(addresses);
		
		
		return consumerDetailsforBooking;
	}

}
