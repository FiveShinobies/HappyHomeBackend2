package com.backend.happyhome.service;

import java.io.IOException;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.backend.happyhome.custom_exceptions.ImageNotUploadedException;
import com.backend.happyhome.dtos.CreateServiceRequestDTOB;
import com.backend.happyhome.entities.HouseholdService;
import com.backend.happyhome.entities.ServiceImage;
import com.backend.happyhome.repository.HouseholdServiceRepo;
import com.backend.happyhome.repository.ServiceImageRepo;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class AdminServiceServiceImpl implements AdminServiceService {

	private final HouseholdServiceRepo serviceRepo;
	private final ServiceImageRepo serviceImageRepo;
	
	@Override
	public Long createService(CreateServiceRequestDTOB request, MultipartFile[] images) throws ImageNotUploadedException {
		
		//Creating a service object
		HouseholdService service = new HouseholdService();
		
		//setting up the fields of service object
		service.setServiceName(request.getServiceName());
		service.setShortDesc(request.getShortDesc());
		service.setLongDesc(request.getLongDesc());
		service.setPrice(request.getPrice());
		service.setCategory(request.getCategory());
		
		//Saving the service in service table
		HouseholdService savedService =  serviceRepo.save(service);
		
		//Saving the images in service Image table
		if(images != null && images.length > 0) {
			for(MultipartFile file : images) {
				try {
					ServiceImage image = new ServiceImage();
					image.setImage(file.getBytes());
					image.setMyService(savedService);
					
					serviceImageRepo.save(image);
				} catch (IOException e) {
					
					throw new ImageNotUploadedException("Failed to add image bytes");
				}
			}
		}
		
		return savedService.getServiceId();
	}

}
