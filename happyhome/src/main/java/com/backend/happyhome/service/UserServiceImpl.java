package com.backend.happyhome.service;

import org.springframework.stereotype.Service;

import com.backend.happyhome.dtos.UserLoginDtoC;
import com.backend.happyhome.dtos.VendorRegisterDtoC;
import com.backend.happyhome.custom_exceptions.UserAlreadyPresentException;
import com.backend.happyhome.custom_exceptions.UserNotPresentException;
import com.backend.happyhome.dtos.ConsumerRegisterDtoC;
import com.backend.happyhome.dtos.ServiceDtoC;
import com.backend.happyhome.entities.Address;
import com.backend.happyhome.entities.Consumer;
import com.backend.happyhome.entities.HouseholdService;
import com.backend.happyhome.entities.Language;
import com.backend.happyhome.entities.User;
import com.backend.happyhome.entities.Vendor;
import com.backend.happyhome.entities.enums.UserRole;
import com.backend.happyhome.repository.AddressRepo;
import com.backend.happyhome.repository.ConsumerRepo;
import com.backend.happyhome.repository.ServiceRepo;
import com.backend.happyhome.repository.UserRepo;
import com.backend.happyhome.repository.VendorRepo;
import com.backend.happyhome.repository.language_repos.LanguageRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	private final UserRepo userRepo;
	private final VendorRepo vendorRepo;
	private final ConsumerRepo consumerRepo;
	private final AddressRepo addressRepo;
	private final ServiceRepo serviceRepo;
	private final LanguageRepository languageRepo;
	
	@Override
	public User isUserPresent(UserLoginDtoC user) throws UserNotPresentException{
		User userFromDb = userRepo.getByEmail(user.getEmail()).orElseThrow(()->new UserNotPresentException());
		if(userFromDb.getPassword().equals(user.getPassword()))
			return userFromDb;
		else
			return null;
	}

	@Override
	public void registerConsumerUser(ConsumerRegisterDtoC user) throws UserAlreadyPresentException {
		// TODO Auto-generated method stub
		if(userRepo.getByEmail(user.getEmail()).isPresent()) {
			throw new UserAlreadyPresentException();
		}
		if(userRepo.getByPhone(user.getPhone()).isPresent()) {
			throw new UserAlreadyPresentException();
		}
		
		User userToDb = new User();
		userToDb.setFirstName(user.getFirstName());
		userToDb.setLastName(user.getLastName());
		userToDb.setEmail(user.getEmail());
		userToDb.setPassword(user.getPassword());
		userToDb.setDob(user.getDob());
		userToDb.setPhone(user.getPhone());
		userToDb.setRole(UserRole.CONSUMER);
		
		Language lang = languageRepo.findByLangName("English").get();
		userToDb.getLanguages().add(lang);
		
		User u = userRepo.save(userToDb);
		
		Consumer consumer = new Consumer();
		consumer.setMyUser(u);
		consumer.setRewardPoints(0);
		
		consumerRepo.save(consumer);
		
		Address address = new Address();
		address.setCity(user.getAddress().getCity());
		address.setHomeNo(user.getAddress().getHomeNo());
		address.setMyUser(u);
		address.setPincode(user.getAddress().getPincode());
		address.setState(user.getAddress().getState());
		address.setTown(user.getAddress().getTown());
		
		addressRepo.save(address);
	}
	
	public void registerVendorUser(VendorRegisterDtoC user) throws UserAlreadyPresentException{
		if(userRepo.getByEmail(user.getEmail()).isPresent()) {
			throw new UserAlreadyPresentException();
		}
		if(userRepo.getByPhone(user.getPhone()).isPresent()) {
			throw new UserAlreadyPresentException();
		}
		User userToDb = new User();
		userToDb.setFirstName(user.getFirstName());
		userToDb.setLastName(user.getLastName());
		userToDb.setEmail(user.getEmail());
		userToDb.setPassword(user.getPassword());
		userToDb.setDob(user.getDob());
		userToDb.setPhone(user.getPhone());
		userToDb.setRole(UserRole.VENDOR);
		
		//duplicate entry and transient property exception has came because of this
		Language lang = languageRepo.findByLangName("English").get();
		userToDb.getLanguages().add(lang);
		
		User u = userRepo.save(userToDb);
		
		Vendor vendor = new Vendor();
		vendor.setMyUser(u);
		vendor.setAadharNo(user.getAadhardNo());
		vendor.setPanNo(user.getPanNo());
		vendor.setExperience(user.getExperience());
		for(ServiceDtoC e : user.getServices()) {
			HouseholdService service = serviceRepo.findByCategoryAndServiceName(e.getCategory(), e.getServiceName());
			vendor.getMyServices().add(service);
		}
		
		
		vendorRepo.save(vendor);
		
		Address address = new Address();
		address.setCity(user.getAddress().getCity());
		address.setHomeNo(user.getAddress().getHomeNo());
		address.setMyUser(u);
		address.setPincode(user.getAddress().getPincode());
		address.setState(user.getAddress().getState());
		address.setTown(user.getAddress().getTown());
		
		addressRepo.save(address);
	}
	
	

}
