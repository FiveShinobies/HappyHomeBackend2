package com.backend.happyhome.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.backend.happyhome.custom_exceptions.ConsumerNotFoundException;
import com.backend.happyhome.custom_exceptions.OrderDoesNotExist;
import com.backend.happyhome.dtos.AddressDto;
import com.backend.happyhome.dtos.ConsumerDtoC;
import com.backend.happyhome.dtos.ConsumerProfileDetailsDTOA;
import com.backend.happyhome.entities.Address;
import com.backend.happyhome.entities.Consumer;
import com.backend.happyhome.entities.Order;
import com.backend.happyhome.entities.User;
import com.backend.happyhome.entities.enums.UserRole;
import com.backend.happyhome.repository.AddressRepo;
import com.backend.happyhome.repository.ConsumerRepo;
import com.backend.happyhome.repository.OrderRepo;
import com.backend.happyhome.repository.UserRepo;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.backend.happyhome.dtos.ConsumerProfileDetailsDTOA;
import com.backend.happyhome.entities.Language;
import com.backend.happyhome.entities.Order;
import com.backend.happyhome.entities.PaymentCard;
import com.backend.happyhome.entities.PaymentUpi;
import com.backend.happyhome.entities.User;
import com.backend.happyhome.entities.UserImage;
import com.backend.happyhome.entities.enums.UserRole;
import com.backend.happyhome.repository.AddressRepo;
import com.backend.happyhome.repository.ConsumerRepo;
import com.backend.happyhome.repository.LanguageRepo;
import com.backend.happyhome.repository.OrderRepo;
import com.backend.happyhome.repository.PaymentCardRepo;
import com.backend.happyhome.repository.PaymentUpiRepo;
import com.backend.happyhome.repository.UserImageRepo;
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
		private final PaymentUpiRepo upiRepo;
	
	private final PaymentCardRepo cardRepo;
	
	private final UserImageRepo imgRepo;
	
	private final LanguageRepo langRepo;

	@Override
	public List<ConsumerDtoC> getAllConsumers() {
		List<User> users = userRepo.findByRole(UserRole.CONSUMER);
		List<ConsumerDtoC> result = new ArrayList<>();
		for(User u : users) {
			ConsumerDtoC dto = new ConsumerDtoC();
			dto.setFirstName(u.getFirstName());
			dto.setLastName(u.getLastName());
			dto.setEmail(u.getEmail());
			dto.setPassword(u.getPassword());
			dto.setPhone(u.getPhone());
			dto.setDob(u.getDob());
			dto.setUserStatus(u.getUserStatus());
			for(Language lang : u.getLanguages()) {
				dto.getLanguages().add(lang.getLangName());
			}
			dto.setRewardPoints(consumerRepo.findByMyUser_UserId(u.getUserId()).getRewardPoints());
			
			AddressDto ad = new AddressDto();
			Address address = addressRepo.findByMyUser(u);
			ad.setCity(address.getCity());
			ad.setHomeNo(address.getHomeNo());
			ad.setPincode(address.getPincode());
			ad.setState(address.getState());
			ad.setTown(address.getTown());
			
			dto.setAddress(ad);
			
			result.add(dto);
		}
		
		return result;
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
		dto.setUserStatus(u.getUserStatus());
		dto.setRewardPoints(c.getRewardPoints());
		
		for(Language lang : u.getLanguages()) {
			dto.getLanguages().add(lang.getLangName());
		}
		
		AddressDto ad = new AddressDto();
		Address address = addressRepo.findByMyUser(u);
		ad.setCity(address.getCity());
		ad.setHomeNo(address.getHomeNo());
		ad.setPincode(address.getPincode());
		ad.setState(address.getState());
		ad.setTown(address.getTown());
		
		dto.setAddress(ad);
		
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
		for(Language lang : user.getLanguages()) {
			dto.getLanguages().add(lang.getLangName());
		}
		user.setLastName(dto.getLastName());
		user.setPassword(dto.getPassword());
		user.setPhone(dto.getPhone());
		user.setUserStatus(dto.getUserStatus());
		
		userRepo.save(user);
		
		c.setRewardPoints(dto.getRewardPoints());
		consumerRepo.save(c);
		
		AddressDto address = dto.getAddress();
		
		Address ad = new Address();
		ad.setCity(address.getCity());
		ad.setHomeNo(address.getHomeNo());
		ad.setPincode(address.getPincode());
		ad.setState(address.getState());
		ad.setTown(address.getTown());
		ad.setMyUser(user);
		
		addressRepo.save(ad);
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

	
	@Override
	public ConsumerProfileDetailsDTOA getConsumerProfileDetailsById(Long cid) {
		
		ConsumerProfileDetailsDTOA cpd = new ConsumerProfileDetailsDTOA();
		
		Optional<Consumer> c = consumerRepo.findById(cid);
		Consumer cr =  c.orElseThrow();
		cpd.setConsumer(cr);
		
		Long uid = cr.getMyUser().getUserId();
		
		List<Address> adrs = addressRepo.findByMyUserUserId(uid);
		cpd.setAddresses(adrs);
		 
		List<PaymentUpi> upis = upiRepo.findByMyUserUserId(uid);
		cpd.setUpis(upis);
		
		List<PaymentCard> cards = cardRepo.findByMyUserUserId(uid);
		cpd.setCards(cards);
		 
		UserImage img =  imgRepo.findById(uid).orElse(null) ; 
		cpd.setImage(img);
		
		List<Language> langs = langRepo.findByUsersUserId(uid);
		cpd.setLanguages(langs);
		
		return cpd;
		
	}

	


}
