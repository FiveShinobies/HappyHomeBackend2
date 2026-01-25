package com.backend.happyhome.service;

import org.springframework.web.multipart.MultipartFile;

import com.backend.happyhome.custom_exceptions.ImageNotUploadedException;
import com.backend.happyhome.dtos.CreateServiceRequestDTOB;

public interface AdminServiceService {

	Long createService(CreateServiceRequestDTOB request, MultipartFile[] images) throws ImageNotUploadedException;
	
}
