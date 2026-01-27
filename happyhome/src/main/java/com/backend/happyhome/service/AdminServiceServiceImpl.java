package com.backend.happyhome.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.backend.happyhome.custom_exceptions.ImageNotUploadedException;
import com.backend.happyhome.custom_exceptions.ServiceNotFoundException;
import com.backend.happyhome.dtos.CreateServiceRequestDTOB;
import com.backend.happyhome.dtos.ServiceDetailsForEditDTOB;
import com.backend.happyhome.dtos.UpdateServiceRequestDTOB;
import com.backend.happyhome.entities.HouseholdService;
import com.backend.happyhome.entities.ServiceImage;
import com.backend.happyhome.entities.Vendor;
import com.backend.happyhome.repository.HouseholdServiceRepo;
import com.backend.happyhome.repository.PaymentUpiRepo;
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
	public ServiceDetailsForEditDTOB getServiceDetailsById(Long sid) {

		HouseholdService service = serviceRepo.findByServiceIdAndActiveTrue(sid)
				.orElseThrow(() -> new ServiceNotFoundException("Service not found with id: " + sid));

		return new ServiceDetailsForEditDTOB(
				service.getServiceId(), 
				service.getServiceName(), 
				service.getShortDesc(),
				service.getLongDesc(), 
				service.getPrice(), 
				service.getCategory()
				);
	}

	@Override
	public Long createService(CreateServiceRequestDTOB request, MultipartFile[] images)
			throws ImageNotUploadedException {

		// Creating a service object
		HouseholdService service = new HouseholdService();

		// setting up the fields of service object
		service.setServiceName(request.getServiceName());
		service.setShortDesc(request.getShortDesc());
		service.setLongDesc(request.getLongDesc());
		service.setPrice(request.getPrice());
		service.setCategory(request.getCategory());

		// Saving the service in service table
		HouseholdService savedService = serviceRepo.save(service);

		// Saving the images in service Image table
		if (images != null && images.length > 0) {
			for (MultipartFile file : images) {
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

	@Override
	public void deleteService(Long sid) {

		HouseholdService service = serviceRepo.findByServiceIdAndActiveTrue(sid)
				.orElseThrow(() -> new ServiceNotFoundException("Service is not found with id: " + sid));
		// soft delete
		service.setActive(false);

//		serviceImageRepo.deleteByMyServiceServiceId(sid);

//		for(Vendor v : service.getMyVendors()) {
//			v.getMyServices().remove(service);
//		}
//		//.getMyVendors().clear();

//		serviceRepo.delete(service);
	}

	@Override
	public void updateService(Long serviceId, UpdateServiceRequestDTOB dto) {

		HouseholdService service = serviceRepo.findByServiceIdAndActiveTrue(serviceId)
				.orElseThrow(() -> new ServiceNotFoundException("Service not found with serviceId: " + serviceId));

		service.setServiceName(dto.getServiceName());
		service.setShortDesc(dto.getShortDesc());
		service.setLongDesc(dto.getLongDesc());
		service.setPrice(dto.getPrice());

	}

}
