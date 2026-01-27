package com.backend.happyhome.service;

import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.backend.happyhome.custom_exceptions.ResourceNotFoundException;
import com.backend.happyhome.dtos.VendorProfileResponseDTOE;
import com.backend.happyhome.repository.OrderRepo;
import com.backend.happyhome.repository.VendorImageRepo;
import com.backend.happyhome.repository.VendorRepo;
import com.backend.happyhome.repository.VendorReviewRepo;
import com.backend.happyhome.entities.HouseholdService;
import com.backend.happyhome.entities.Language;
import com.backend.happyhome.entities.User;
import com.backend.happyhome.entities.Vendor;
import java.util.Base64;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class VendorDetailsServiceImpl implements VendorDetailsService {

	private final VendorRepo vendorRepository;
	private final OrderRepo orderRepository;
	private final VendorReviewRepo vendorReviewRepository;
	private final VendorImageRepo vendorImageRepository;
	
	@Override
	public VendorProfileResponseDTOE getVendorDetails(Long vendorId) {
		
		Vendor vendor = vendorRepository.findById(vendorId)
				.orElseThrow(() -> new ResourceNotFoundException("Invalid Vendor Id"));
		
		VendorProfileResponseDTOE response = new VendorProfileResponseDTOE();
		
		User user = vendor.getMyUser();
		
		response.setFirstName(user.getFirstName());
		response.setLastName(user.getLastName());
		response.setFullName(user.getFirstName() + " " + user.getLastName());
		response.setEmail(user.getEmail());
		response.setPhone(user.getPhone());
		response.setDateOfBirth(user.getDob());
		
		response.setServicesProvided(vendor.getMyServices().stream().map(HouseholdService::getServiceName).collect(Collectors.toList()));
		response.setTotalServices(orderRepository.countByMyVendorVendorId(vendorId));
		response.setTotalReviews(vendorReviewRepository.countByMyOrderMyVendorVendorId(vendorId));
		response.setRating(vendorReviewRepository.findAverageRating(vendorId));
		
		response.setLanguages(
			    vendor.getMyUser()
			          .getLanguages()
			          .stream()
			          .map(Language::getLangName)
			          .toList()
			);

		
		
		response.setAadhaarNumber(mask(vendor.getAadharNo()));
		response.setPanNumber(mask(vendor.getPanNo()));
		
		
		vendorImageRepository.findByMyUserUserId(user.getUserId())
        .ifPresent(userImage -> {
            if (userImage.getImage() != null) {
                response.setImageBase64(
                    Base64.getEncoder()
                          .encodeToString(userImage.getImage())
                );
            }
        });

		return response;
	}
	private String mask(String value) {
        if (value == null || value.length() < 4) {
            return "XXXX";
        }
        return "XXXX XXXX " + value.substring(value.length() - 4);
    }
}
