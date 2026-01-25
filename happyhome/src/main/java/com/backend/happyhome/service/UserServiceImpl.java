package com.backend.happyhome.service;

import org.springframework.stereotype.Service;

import com.backend.happyhome.dtos.UserLoginDtoC;
import com.backend.happyhome.dtos.VendorRegisterDtoC;
import com.backend.happyhome.custom_exceptions.UserAlreadyPresentException;
import com.backend.happyhome.custom_exceptions.UserNotPresentException;
import com.backend.happyhome.dtos.ConsumerRegisterDtoC;
import com.backend.happyhome.entities.Consumer;
import com.backend.happyhome.entities.User;
import com.backend.happyhome.entities.Vendor;
import com.backend.happyhome.entities.enums.UserRole;
import com.backend.happyhome.repository.ConsumerRepo;
import com.backend.happyhome.repository.UserRepo;
import com.backend.happyhome.repository.VendorRepo;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	private final UserRepo userRepo;
	private final VendorRepo vendorRepo;
	private final ConsumerRepo consumerRepo;
	
	@Override
	public User isUserPresent(UserLoginDtoC user) throws UserNotPresentException{
		
		User userFromDb = userRepo.getByEmail(user.getEmail()).orElseThrow(()->new UserNotPresentException());
		return userFromDb;
	}

	@Override
	public void registerConsumerUser(ConsumerRegisterDtoC user) throws UserAlreadyPresentException {
		// TODO Auto-generated method stub
		userRepo.getByEmail(user.getEmail()).orElseThrow(()-> new UserAlreadyPresentException());
		userRepo.getByPhone(user.getPhone()).orElseThrow(()->new UserAlreadyPresentException());
		
		User userToDb = new User();
		userToDb.setFirstName(user.getFirstName());
		userToDb.setLastName(user.getLastName());
		userToDb.setEmail(user.getEmail());
		userToDb.setPassword(user.getPassword());
		userToDb.setDob(user.getDob());
		userToDb.setPhone(user.getPhone());
		userToDb.setRole(UserRole.CONSUMER);
		
		User u = userRepo.save(userToDb);
		
		Consumer consumer = new Consumer();
		consumer.setMyUser(u);
		consumer.setRewardPoints(0);
		
		consumerRepo.save(consumer);
	}
	
	public void registerVendorUser(VendorRegisterDtoC user) throws UserAlreadyPresentException{
		userRepo.getByEmail(user.getEmail()).orElseThrow(()-> new UserAlreadyPresentException());
		userRepo.getByPhone(user.getPhone()).orElseThrow(()->new UserAlreadyPresentException());
		
		User userToDb = new User();
		userToDb.setFirstName(user.getFirstName());
		userToDb.setLastName(user.getLastName());
		userToDb.setEmail(user.getEmail());
		userToDb.setPassword(user.getPassword());
		userToDb.setDob(user.getDob());
		userToDb.setPhone(user.getPhone());
		userToDb.setRole(UserRole.VENDOR);
		
		User u = userRepo.save(userToDb);
		
		Vendor vendor = new Vendor();
		vendor.setMyUser(u);
		vendor.setAadharNo(user.getAadhardNo());
		vendor.setPanNo(user.getPanNo());
		vendor.setExperience(user.getExperience());
		vendor.setMyServices(user.getServices());
		vendor.setMyUser(userRepo.getByEmail(userToDb.getEmail()).get());
		vendorRepo.save(vendor);
	}
	
	

}
