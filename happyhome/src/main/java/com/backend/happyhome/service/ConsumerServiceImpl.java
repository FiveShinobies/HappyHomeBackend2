package com.backend.happyhome.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.happyhome.dtos.ConsumerProfileDetailsDTOA;
import com.backend.happyhome.entities.Address;
import com.backend.happyhome.entities.Consumer;
import com.backend.happyhome.entities.Language;
import com.backend.happyhome.entities.PaymentCard;
import com.backend.happyhome.entities.PaymentUpi;
import com.backend.happyhome.entities.UserImage;
import com.backend.happyhome.repository.AddressRepo;
import com.backend.happyhome.repository.ConsumerRepo;
import com.backend.happyhome.repository.LanguageRepo;
import com.backend.happyhome.repository.PaymentCardRepo;
import com.backend.happyhome.repository.PaymentUpiRepo;
import com.backend.happyhome.repository.UserImageRepo;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class ConsumerServiceImpl implements ConsumerService {
	
	private final ConsumerRepo consumerRepo;
	
	private final AddressRepo addressRepo; 
	
	private final PaymentUpiRepo upiRepo;
	
	private final PaymentCardRepo cardRepo;
	
	private final UserImageRepo imgRepo;
	
	private final LanguageRepo langRepo;
	
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
