package com.backend.happyhome.controller;

import java.util.Map;

import org.apache.commons.codec.digest.HmacUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.backend.happyhome.dtos.RazorpayPaymentDTOA;
import com.backend.happyhome.service.User_service.RazorpayService;
import com.razorpay.Order;
import com.razorpay.RazorpayException;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/payments")
@CrossOrigin(origins = "*")
public class PaymentController {

	private final RazorpayService rpService;
	
	@PostMapping("/create-order")
	public ResponseEntity<Map<String , Object>> createOrder(@RequestParam int amount, @RequestParam String currency) throws RazorpayException 
	{

	    Order order = rpService.createOrder(amount, currency, "receipt_001");
	    
	    return ResponseEntity.ok(order.toJson().toMap()); 
	}

	
	@PostMapping("/verify")
	public void verifyPayment(@RequestBody RazorpayPaymentDTOA razorpayResponse) {
	
		System.out.println("Order ID    : " + razorpayResponse.getRazorpayOrderId());
		System.out.println("Payment ID  : " + razorpayResponse.getRazorpayPaymentId());
		System.out.println("Signature   : " + razorpayResponse.getRazorpaySignature());
		
		String data = 	razorpayResponse.getRazorpayOrderId() + "|" + razorpayResponse.getRazorpayPaymentId();
		
		String genSig = new HmacUtils("HmacSHA256" , rpService.getApiSecret()).hmacHex(data) ;
		
		if(genSig.equals(razorpayResponse.getRazorpaySignature())) {
			System.out.println("Verification successfull");
		}else {
			System.out.println("Verification failed");
		}
		
	}
	
	
}



//Order ID    : order_S7hcjrtCxYpQ8q
//Payment ID  : pay_S7hdRaUVKRq5Kg
//Signature   : 11c49187519c6aa29668b1f96b118612c068c1375a6397daac85dfa0a1d61f19
//Verification successfull
