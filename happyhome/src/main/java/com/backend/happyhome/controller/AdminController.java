package com.backend.happyhome.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.happyhome.dtos.CreateServiceRequestDTOB;
import com.backend.happyhome.dtos.HouseholdServicesListDTOB;
import com.backend.happyhome.dtos.ServiceDetailsDTOB;
import com.backend.happyhome.dtos.ServiceDetailsForEditDTOB;
import com.backend.happyhome.dtos.UpdateServiceRequestDTOB;
import com.backend.happyhome.service.AdminServiceService;
import com.backend.happyhome.service.HouseholdServiceService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

	private final HouseholdServiceService householdService;

	private final AdminServiceService adminServiceService;

	@GetMapping("/services")
	public ResponseEntity<List<HouseholdServicesListDTOB>> getAllServices() {
		return ResponseEntity.ok(householdService.getAllHouseholdServices());
	}

	@GetMapping("/services/{id}")
	public ResponseEntity<ServiceDetailsDTOB> getServiceById(@PathVariable Long id) {
		return ResponseEntity.ok(householdService.getServiceById(id));
	}

	@GetMapping("/service-form/{id}")
	public ResponseEntity<ServiceDetailsForEditDTOB> getServiceFormById(@PathVariable Long id) {
		return ResponseEntity.ok(adminServiceService.getServiceDetailsById(id));
	}
	
	@PostMapping("/service/add")
	public ResponseEntity<?> createService(@Valid @RequestBody CreateServiceRequestDTOB request) {

		Long serviceIdLong = adminServiceService.createService(request, null);

		return ResponseEntity.status(HttpStatus.CREATED)
				.body(Map.of("message", "Service created successfully", "service", serviceIdLong));
	}

	@DeleteMapping("/service/{id}")
	public ResponseEntity<?> deleteService(@PathVariable Long id) {

		adminServiceService.deleteService(id);

		return ResponseEntity.ok(Map.of("message", "Service Deleted successfully"));
	}

	@PutMapping("/service/{serviceId}")
	public ResponseEntity<?> updateService(@PathVariable Long serviceId,
			@Valid @RequestBody UpdateServiceRequestDTOB request) {

		adminServiceService.updateService(serviceId, request);

		return ResponseEntity.status(HttpStatus.CREATED)
				.body(Map.of("message","Service updated successfully", "service", serviceId));

	}

}
