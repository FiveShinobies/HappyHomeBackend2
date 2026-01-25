package com.backend.happyhome.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.happyhome.dtos.OrderDtoC;
import com.backend.happyhome.entities.Address;
import com.backend.happyhome.service.OrderService;
import com.backend.happyhome.service.VendorService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/Vendor")
@RequiredArgsConstructor
public class VendorController {

	private final OrderService orderService;
	private final VendorService vendorService;
	
	@GetMapping
	ResponseEntity<List<OrderDtoC>> getIncomingRequest(){
		List<OrderDtoC> list = orderService.getIncomingOrderRequest();
		
		return new ResponseEntity<>(list,HttpStatus.OK);
	}
	
	@PostMapping
	ResponseEntity<String> acceptRequest(@RequestBody Long oId, @RequestBody Long vId){
		boolean hasBeenAccepted = vendorService.acceptRequest(oId, vId);
		if(hasBeenAccepted) {
			return new ResponseEntity<>("Request Accepted", HttpStatus.ACCEPTED);
		}else {
			return new ResponseEntity<>("Request Not Accepted", HttpStatus.NOT_ACCEPTABLE);
		}
	}
	
	@GetMapping("/details/{id}")
	ResponseEntity<OrderDtoC> getOngoingOrderDetails(@PathVariable Long oId){
		return new ResponseEntity<>(orderService.getOngoingOrders(oId),HttpStatus.OK);
	}
	
	@GetMapping("/{id}/address")
	ResponseEntity<Address> getAddressOfOrder(@PathVariable Long oId){
		return new ResponseEntity<>(vendorService.getAddressOfOrder(oId),HttpStatus.OK);
	}
	
	
}
