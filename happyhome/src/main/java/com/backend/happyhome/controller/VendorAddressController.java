package com.backend.happyhome.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.happyhome.dtos.VendorAddressResponseDTOE;
import com.backend.happyhome.service.VendorAddressService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/vendor")
@RequiredArgsConstructor
public class VendorAddressController {
	
	    private final VendorAddressService vendorAddressService;

	    @GetMapping("/{vendorId}/address")
	    public ResponseEntity<VendorAddressResponseDTOE> getVendorAddress(
	            @PathVariable Long vendorId) {

	        return ResponseEntity.ok(
	                vendorAddressService.getVendorAddress(vendorId)
	        );
	    }
	}

