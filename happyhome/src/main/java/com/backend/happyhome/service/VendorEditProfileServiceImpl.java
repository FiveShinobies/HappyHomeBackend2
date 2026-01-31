package com.backend.happyhome.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.backend.happyhome.custom_exceptions.ResourceNotFoundException;
import com.backend.happyhome.dtos.VendorEditProfileRequestDTOE;
import com.backend.happyhome.entities.Address;
import com.backend.happyhome.entities.HouseholdService;
import com.backend.happyhome.entities.User;
import com.backend.happyhome.entities.Vendor;
import com.backend.happyhome.repository.LanguageRepo;
import com.backend.happyhome.repository.ServiceRepo;
import com.backend.happyhome.repository.VendorAddressRepo;
import com.backend.happyhome.repository.VendorRepo;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class VendorEditProfileServiceImpl implements VendorEditProfileService {

	private final VendorRepo vendorRepository;
	private final VendorAddressRepo vendorAddressRepository;
	private final ServiceRepo serviceRepository;
	private final LanguageRepo languageRepository;
	
	@Override
	public void editProfile(Long vendorId, VendorEditProfileRequestDTOE request) {
		
		Vendor vendor = vendorRepository.findById(vendorId)
				.orElseThrow(() -> new ResourceNotFoundException("Invalid Vendor ID"));
		
		User user = vendor.getMyUser();
		
		if(request.getFirstName() != null)
			user.setFirstName(request.getFirstName());
		
		if(request.getLastName() != null)
			user.setLastName(request.getLastName());
		
		if(request.getEmail() != null)
			user.setEmail(request.getEmail());
		
		if(request.getPhone() != null)
			user.setPhone(request.getPhone());
		
		if(request.getDob() != null)
			user.setDob(request.getDob());
		
		//Address Edit
		Address address = vendorAddressRepository.findByMyUserUserId(user.getUserId())
				.orElseThrow(() -> new ResourceNotFoundException("Address Not Found"));
		
		if (request.getHomeNo() != null)
            address.setHomeNo(request.getHomeNo());

        if (request.getCity() != null)
            address.setCity(request.getCity());

        if (request.getState() != null)
            address.setState(request.getState());

        if (request.getPincode() != null)
            address.setPincode(request.getPincode());
        
     // 🔹 UPDATE EXPERIENCE
        if (request.getExperience() != null) {
            vendor.setExperience(request.getExperience());
        }

        // 🔹 UPDATE SERVICES (BY NAME)
        if (request.getServiceName() != null && !request.getServiceName().isEmpty()) {

            Set<HouseholdService> services =
                new HashSet<>(serviceRepository.findByServiceNameIn(request.getServiceName()));

            if (services.size() != request.getServiceName().size()) {
                throw new ResourceNotFoundException("One or more services not found");
            }

            vendor.setMyServices(services);
        }

//        // 🔹 UPDATE LANGUAGES (BY NAME)
//        if (request.getLanguageName() != null && !request.getLanguageName().isEmpty()) {
//
//            Set<Language> languages =
//                new HashSet<>(languageRepository.findByLangNameIn(request.getLanguageName()));
//
//            if (languages.size() != request.getLanguageName().size()) {
//                throw new ResourceNotFoundException("One or more languages not found");
//            }
//            vendor.getMyUser().setLanguages(languages);
//        
        
//	}
        vendorRepository.save(vendor);
}
}

//package com.backend.happyhome.service;
//
//import java.util.Set;
//import java.util.stream.Collectors;
//
//import org.springframework.stereotype.Service;
//
//import com.backend.happyhome.custom_exceptions.ResourceNotFoundException;
//import com.backend.happyhome.dtos.VendorEditProfileRequestDTOE;
//import com.backend.happyhome.entities.Address;
//import com.backend.happyhome.entities.HouseholdService;
//import com.backend.happyhome.entities.Language;
//import com.backend.happyhome.entities.User;
//import com.backend.happyhome.entities.Vendor;
//import com.backend.happyhome.repository.LanguageRepo;
//import com.backend.happyhome.repository.ServiceRepo;
//import com.backend.happyhome.repository.VendorAddressRepo;
//import com.backend.happyhome.repository.VendorRepo;
//
//import jakarta.transaction.Transactional;
//import lombok.RequiredArgsConstructor;
//
//@Service
//@Transactional
//@RequiredArgsConstructor
//public class VendorEditProfileServiceImpl implements VendorEditProfileService {
//
//	private final VendorRepo vendorRepository;
//	private final VendorAddressRepo vendorAddressRepository;
//	private final ServiceRepo serviceRepository;
//	private final LanguageRepo languageRepository;
//	
//	@Override
//	public void editProfile(Long vendorId, VendorEditProfileRequestDTOE request) {
//		
//		Vendor vendor = vendorRepository.findById(vendorId)
//				.orElseThrow(() -> new ResourceNotFoundException("Invalid Vendor ID"));
//		
//		User user = vendor.getMyUser();
//		
//		if(request.getFirstName() != null)
//			user.setFirstName(request.getFirstName());
//		
//		if(request.getLastName() != null)
//			user.setLastName(request.getLastName());
//		
//		if(request.getEmail() != null)
//			user.setEmail(request.getEmail());
//		
//		if(request.getPhone() != null)
//			user.setPhone(request.getPhone());
//		
//		if(request.getDob() != null)
//			user.setDob(request.getDob());
//		
//		//Address Edit
//		Address address = vendorAddressRepository.findByMyUserUserId(user.getUserId())
//				.orElseThrow(() -> new ResourceNotFoundException("Address Not Found"));
//		
//		if (request.getHomeNo() != null)
//            address.setHomeNo(request.getHomeNo());
//
//        if (request.getCity() != null)
//            address.setCity(request.getCity());
//
//        if (request.getState() != null)
//            address.setState(request.getState());
//
//        if (request.getPincode() != null)
//            address.setPincode(request.getPincode());
//        
//        //Services Edit
//        if(request.getServiceIds() != null) {
//        	Set<HouseholdService> services = serviceRepository.findAllById(request.getServiceIds())
//        			.stream().collect(Collectors.toSet());
//        	vendor.setMyServices(services);
//        }
//        
//        //Languages Edit
//        if (request.getLanguageIds() != null) {
//            Set<Language> languages =
//                    languageRepository.findAllById(request.getLanguageIds())
//                                .stream().collect(Collectors.toSet());
//            user.setLanguages(languages);
//        }
//        
//        vendorRepository.save(vendor);
//	}
//
//}
