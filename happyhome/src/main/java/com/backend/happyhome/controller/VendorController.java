package com.backend.happyhome.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.happyhome.dtos.VendorAddressResponseDTOE;
import com.backend.happyhome.dtos.VendorBankingResponseDTOE;
import com.backend.happyhome.dtos.VendorEditProfileRequestDTOE;
import com.backend.happyhome.dtos.VendorFeedbackRequestDTOE;
import com.backend.happyhome.dtos.VendorProfileResponseDTOE;
import com.backend.happyhome.service.VendorAddressService;
import com.backend.happyhome.service.VendorBankingService;
import com.backend.happyhome.service.VendorDetailsService;
import com.backend.happyhome.service.VendorEditProfileService;
import com.backend.happyhome.service.VendorReviewService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import java.util.List;


import com.backend.happyhome.dtos.OrderDtoC;
import com.backend.happyhome.service.OrderService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/vendor")
@RequiredArgsConstructor
public class VendorController {
  	private final VendorDetailsService vendorDetailsService;
    private final VendorEditProfileService vendorEditProfileService;
    private final VendorBankingService vendorBankingService;
    private final VendorAddressService vendorAddressService;
    private final VendorReviewService vendorReviewService;
    private final OrderService orderService;
    //  Get Vendor Profile 
    @GetMapping("/{vendorId}/profile")
    public ResponseEntity<VendorProfileResponseDTOE> getVendorProfile(
            @PathVariable Long vendorId) {

        return ResponseEntity.ok(
                vendorDetailsService.getVendorDetails(vendorId)
        );
    }

    //  Get Vendor Address
    @GetMapping("/{vendorId}/address")
    public ResponseEntity<VendorAddressResponseDTOE> getVendorAddress(
            @PathVariable Long vendorId) {

        return ResponseEntity.ok(
                vendorAddressService.getVendorAddress(vendorId)
        );
    }

    //  Get Vendor Banking Details
    @GetMapping("/{vendorId}/banking")
    public ResponseEntity<VendorBankingResponseDTOE> getVendorBankingDetails(
            @PathVariable Long vendorId) {

        return ResponseEntity.ok(
                vendorBankingService.getVendorBankingDetails(vendorId)
        );
    }

    //  Edit Vendor Profile (PATCH)
    @PatchMapping("/{vendorId}/profile")
    public ResponseEntity<String> editVendorProfile(
            @PathVariable Long vendorId,
            @RequestBody VendorEditProfileRequestDTOE request) {

        vendorEditProfileService.editProfile(vendorId, request);
        return ResponseEntity.ok("Vendor profile updated successfully");
    }

    //  Give feedback to consumer 
    @PostMapping("/orders/{orderId}/feedback")
    public ResponseEntity<String> giveFeedbackToConsumer(
            @PathVariable Long orderId,
            @RequestBody VendorFeedbackRequestDTOE request) {

        request.setOrderId(orderId);

        vendorReviewService.giveFeedback(request);

        return ResponseEntity.ok("Feedback submitted successfully");
    }

	
  	    
	    @GetMapping("/work")
	    public ResponseEntity<List<OrderDtoC>> sendWorkNOtification(){

	    	List<OrderDtoC> luo =  orderService.getIncomingOrderRequest();
	    	
	    	return ResponseEntity.ok(luo);
	    }
	    
	    @PostMapping("/work")
	    public ResponseEntity<?> acceptWork(@RequestBody Long vendorId , @RequestBody Long orderId){
	    //to be completed
	    	return ResponseEntity.ok(null);
	    }
	    
	    
}

