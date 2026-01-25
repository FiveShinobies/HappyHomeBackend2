package com.backend.happyhome.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.backend.happyhome.dtos.CreateServiceRequestDTOB;
import com.backend.happyhome.dtos.HouseholdServicesListDTOB;
import com.backend.happyhome.dtos.ServiceDetailsDTOB;
import com.backend.happyhome.service.AdminServiceService;
import com.backend.happyhome.service.HouseholdServiceService;


import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

private final HouseholdServiceService householdService;

private final AdminServiceService adminServiceService;
	
	@GetMapping("/services")
	public ResponseEntity<List<HouseholdServicesListDTOB>> getAllServices(){
		return ResponseEntity.ok(householdService.getAllHouseholdServices());
	}
	
	@GetMapping("/services/{id}")
	public ResponseEntity<ServiceDetailsDTOB> getServiceById(
			@PathVariable Long id){
		return ResponseEntity.ok(householdService.getServiceById(id));
	}
	
	@PostMapping(
			value = "/service/add",
			consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<?> createService(
			@RequestPart("service") CreateServiceRequestDTOB request,
			@RequestPart(value="images",required = false) MultipartFile[] images){
		
		Long serviceIdLong = adminServiceService.createService(request, images);
		
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(Map.of(
						"message","Service created successfully",
						"service",serviceIdLong));
	}
	
}
