package com.backend.happyhome.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.happyhome.dtos.AdminEditVendorRequestDTOE;
import com.backend.happyhome.dtos.AdminOrderDetailsDTOE;
import com.backend.happyhome.dtos.VendorDetailsAdminDTOE;
import com.backend.happyhome.dtos.VendorSummaryDTOE;
import com.backend.happyhome.service.AdminVendorService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/admin/vendors")
@RequiredArgsConstructor
public class AdminController {


	    private final AdminVendorService adminVendorService;

	    // Get all vendors
	    @GetMapping
	    public ResponseEntity<List<VendorSummaryDTOE>> getAllVendors() {
	        return ResponseEntity.ok(adminVendorService.getAllVendors());
	    }

	    //  Get vendor details by ID
	    @GetMapping("/{vendorId}")
	    public ResponseEntity<VendorDetailsAdminDTOE> getVendorDetailsById(
	            @PathVariable Long vendorId) {

	        return ResponseEntity.ok(
	                adminVendorService.getVendorDetailsById(vendorId)
	        );
	    }

	    //  Edit vendor details 
	    @PatchMapping("/{vendorId}")
	    public ResponseEntity<String> editVendorDetails(
	            @PathVariable Long vendorId,
	            @RequestBody AdminEditVendorRequestDTOE request) {

	        adminVendorService.editVendorDetails(vendorId, request);
	        return ResponseEntity.ok("Vendor updated successfully");
	    }

	    // Get vendor orders
	    @GetMapping("/{vendorId}/orders")
	    public ResponseEntity<List<AdminOrderDetailsDTOE>> getVendorOrders(
	            @PathVariable Long vendorId) {

	        return ResponseEntity.ok(
	                adminVendorService.getVendorOrders(vendorId)
	        );
	    }
	}

