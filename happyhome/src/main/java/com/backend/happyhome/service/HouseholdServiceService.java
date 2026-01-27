package com.backend.happyhome.service;

import java.util.List;

import com.backend.happyhome.dtos.HouseholdServicesListDTOB;
import com.backend.happyhome.dtos.ServiceDetailsDTOB;

public interface HouseholdServiceService {
	
	List<HouseholdServicesListDTOB> getAllHouseholdServices();
	
	ServiceDetailsDTOB getServiceById(Long serviceId);

}
