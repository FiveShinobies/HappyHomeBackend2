package com.backend.happyhome.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.happyhome.dtos.OrderDtoC;
import com.backend.happyhome.dtos.VendorAddressResponseDTOE;
import com.backend.happyhome.service.OrderService;
import com.backend.happyhome.service.VendorAddressService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/vendor")
@RequiredArgsConstructor
public class VendorController {
	
  private final VendorAddressService vendorAddressService;
	
  private final OrderService orderService;
	  
	    @GetMapping("/{vendorId}/address")
	    public ResponseEntity<VendorAddressResponseDTOE> getVendorAddress(
	            @PathVariable Long vendorId) {
	    	

	        return ResponseEntity.ok(
	                vendorAddressService.getVendorAddress(vendorId)
	        );
	    }
	
	    
	    @GetMapping("/work")
	    public ResponseEntity<List<OrderDtoC>> sendWorkNOtification(){

	    	List<OrderDtoC> luo =  orderService.getIncomingOrderRequest();
	    	
	    	return ResponseEntity.ok(luo);
	    }
	    
	    @PostMapping("/work")
	    public ResponseEntity<?> acceptWork(@RequestBody Long vendorId , @RequestBody Long orderId){
	    	
	    	return ResponseEntity.ok(null);
	    }
	    
	    
}

