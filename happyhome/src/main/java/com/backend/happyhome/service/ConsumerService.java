package com.backend.happyhome.service;

import com.backend.happyhome.dtos.ConsumerProfileDetailsDTOA;

public interface ConsumerService {

	ConsumerProfileDetailsDTOA getConsumerProfileDetailsById(Long cid);

	
}


