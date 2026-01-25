package com.backend.happyhome.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.happyhome.dtos.OrderDtoC;
import com.backend.happyhome.service.OrderService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/Vendor")
@RequiredArgsConstructor
public class VendorController {

	private final OrderService orderService;
	
	@GetMapping()
	ResponseEntity<List<OrderDtoC>> getIncomingRequest(){
		List<OrderDtoC> list = orderService.getIncomingOrderRequest();
		
		return new ResponseEntity<>(list,HttpStatus.OK);
	}
	
}
