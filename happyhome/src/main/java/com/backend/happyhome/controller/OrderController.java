package com.backend.happyhome.controller;

import java.time.LocalDateTime;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.backend.happyhome.dtos.ConsumerReviewDTOA;
import com.backend.happyhome.service.OrderService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {
	
	private final OrderService orderService;

	@PatchMapping("/in-progress/{oid}")
	public ResponseEntity<String> updateStatusToInProgresss(@RequestParam Long oid){
		
		orderService.updateStatusToInProgress(oid);
		
		return ResponseEntity.ok("Status updated to InProgress");
	}
	
	@PatchMapping("/completed/{oid}")
	public ResponseEntity<String> updateStatusToCompleted(@RequestParam Long oid){
		
		orderService.updateStatusToCompleted(oid);
		
		return ResponseEntity.ok("Status updated to Completed");
	}

	@PatchMapping("/change-timeslot/{oId}")
	public ResponseEntity<?> changeTimeSlot(@PathVariable Long oId , @RequestBody LocalDateTime newTime){
		
		return ResponseEntity.ok(orderService.changeTimeSlot(oId, newTime));
	}
	
	@PostMapping("/add-consumer-review")
	public ResponseEntity<?> addConsumerReview(Long oid, ConsumerReviewDTOA cr) {
		return ResponseEntity.ok(orderService.addConsumerReviewForAnOrder(oid, cr));
	}
	
	
}
