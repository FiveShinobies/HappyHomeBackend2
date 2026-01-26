package com.backend.happyhome.controller;


import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.happyhome.dtos.ConsumerDtoC;
import com.backend.happyhome.entities.Consumer;
import com.backend.happyhome.entities.Order;
import com.backend.happyhome.service.ConsumerService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/consumer")
@RequiredArgsConstructor
public class ConsumerController {

	private final ConsumerService consumerService;
	
	@GetMapping("/all")
	ResponseEntity<List<ConsumerDtoC>> getAllConsumers(){
		return new ResponseEntity<>(consumerService.getAllConsumers(),HttpStatus.OK);
	}
	
	@PutMapping("/{id}")
	ResponseEntity<ConsumerDtoC> editConsumerDetails(@RequestBody ConsumerDtoC consumer,@PathVariable Long id){
		return new ResponseEntity<>(consumerService.editConsumerDetails(consumer, id),HttpStatus.OK);
	}
	
	@GetMapping("/{id}/allOrders")
	ResponseEntity<List<Order>> getAllOrdersOfConsumer(@PathVariable Long id){
		return new ResponseEntity<>(consumerService.getAllOrdersOfConsumer(id),HttpStatus.OK);
	}
	
	@GetMapping("/order/{oId}")
	ResponseEntity<Order> getOrderOfConsumer(@PathVariable Long oId){
		return new ResponseEntity<>(consumerService.getOrderOfConsumer(oId),HttpStatus.OK);
	}
	
	@GetMapping("/profile/{oId}")
	public ResponseEntity<?> getConsumerProfile(@PathVariable Long oId){
		return ResponseEntity.ok(consumerService.getConsumerProfileDetailsById(oId));	
	}

	
	
}
