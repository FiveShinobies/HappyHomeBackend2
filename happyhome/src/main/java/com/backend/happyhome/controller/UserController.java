package com.backend.happyhome.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.happyhome.dtos.ConsumerRegisterDtoC;
import com.backend.happyhome.dtos.LoginResponseDtoC;
import com.backend.happyhome.dtos.UserLoginDtoC;
import com.backend.happyhome.dtos.VendorRegisterDtoC;
import com.backend.happyhome.entities.User;
import com.backend.happyhome.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class UserController {

	private final UserService userService;
	
	
	@PostMapping("/login")
	ResponseEntity<LoginResponseDtoC> login(@RequestBody UserLoginDtoC userDto){
		User user = userService.isUserPresent(userDto);
		if(user != null) {
			HttpHeaders headers = new HttpHeaders();
			headers.add("Role", user.getRole().toString());
			ResponseEntity<LoginResponseDtoC> res = new ResponseEntity<LoginResponseDtoC>(new LoginResponseDtoC("Success",userService.giveRespectiveId(user.getUserId())), headers, HttpStatus.OK);
			return res;
		}else {
			return new ResponseEntity<>(new LoginResponseDtoC("Failed",null),HttpStatus.NOT_FOUND);
		}
	}
	
	
	@PostMapping("/signup/consumer")
	ResponseEntity<String> signup(@RequestBody ConsumerRegisterDtoC user){
		userService.registerConsumerUser(user);
		return new ResponseEntity<String>("Consumer Added",HttpStatus.CREATED);
	}
	
	@PostMapping("/signup/vendor")
	ResponseEntity<String> signup(@RequestBody VendorRegisterDtoC user){
		userService.registerVendorUser(user);
		return new ResponseEntity<String>("Vendor Added",HttpStatus.CREATED);
	}
}

