package com.backend.happyhome.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.backend.happyhome.custom_exceptions.ResourceNotFoundException;
import com.backend.happyhome.dtos.VendorBankingResponseDTOE;
import com.backend.happyhome.entities.PaymentUpi;
import com.backend.happyhome.entities.Vendor;
import com.backend.happyhome.entities.VendorBanking;
import com.backend.happyhome.repository.PaymentUpiRepo;
import com.backend.happyhome.repository.VendorBankingRepo;
import com.backend.happyhome.repository.VendorRepo;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class VendorBankingServiceImpl implements VendorBankingService {

	private final VendorRepo vendorRepository;
	private final VendorBankingRepo vendorBankingRepository;
	private final PaymentUpiRepo paymentUpiRepository;
	@Override
	public VendorBankingResponseDTOE getVendorBankingDetails(Long vendorId) {
		
		Vendor vendor = vendorRepository.findById(vendorId)
                .orElseThrow(() -> new ResourceNotFoundException("Invalid vendor id"));
		
		VendorBanking banking = vendorBankingRepository.findByMyVendorVendorId(vendorId)
                .orElseThrow(() -> new ResourceNotFoundException("Banking details not found"));
		
		 List<PaymentUpi> upi = paymentUpiRepository.findByMyUserUserId(vendor.getMyUser().getUserId());
	             
		 
		 VendorBankingResponseDTOE response = new VendorBankingResponseDTOE();
		 
		 response.setBankName(banking.getBankName());
	        response.setBranchName(banking.getBranchName());
	        response.setIfscCode(banking.getIfscCode());
	        response.setHolderName(banking.getHolderName());
	        response.setAccountNo(maskAccountNo(banking.getAccountNo()));

	        if (upi != null) {
	            response.setUpiId(maskUpi(upi.get(0).getUpiAddress()));
	        }

	        return response;
	}
	
	private String maskAccountNo(String accNo) {
        if (accNo == null || accNo.length() < 4) {
            return "XXXX";
        }
        return "XXXXXX" + accNo.substring(accNo.length() - 4);
    }

	private String maskUpi(String upi) {
        if (upi == null || !upi.contains("@")) {
            return "XXXX@upi";
        }
        return "XXXX@" + upi.substring(upi.indexOf("@") + 1);
    }
}
