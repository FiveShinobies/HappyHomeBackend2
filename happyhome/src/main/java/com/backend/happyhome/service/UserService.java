package com.backend.happyhome.service;

import com.backend.happyhome.dtos.UserLoginDtoC;
import com.backend.happyhome.dtos.ConsumerRegisterDtoC;
import com.backend.happyhome.entities.User;

public interface UserService {

	User isUserPresent(UserLoginDtoC user);
	void registerConsumerUser(ConsumerRegisterDtoC user);
}
