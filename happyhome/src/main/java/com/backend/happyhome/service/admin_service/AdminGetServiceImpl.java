package com.backend.happyhome.service.admin_service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.backend.happyhome.dtos.admin_dto.ServiceDtoD;
import com.backend.happyhome.dtos.admin_dto.ServiceImageDtoD;
import com.backend.happyhome.entities.HouseholdService;
import com.backend.happyhome.repository.HouseholdServiceRepo;
import com.backend.happyhome.repository.service_repos.ServiceImageRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AdminGetServiceImpl implements AdminGetService {

    private final HouseholdServiceRepo serviceRepository;
    private final ServiceImageRepository serviceImageRepository;

    @Override
    public List<ServiceDtoD> getAllServices() {

        return serviceRepository.findAll()
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    private ServiceDtoD mapToDto(HouseholdService service) {

        List<ServiceImageDtoD> images =
                serviceImageRepository
                        .findByMyService_ServiceId(service.getServiceId())
                        .stream()
                        .map(img -> new ServiceImageDtoD(img.getServiceImageId()))
                        .collect(Collectors.toList());

        return new ServiceDtoD(
                service.getServiceId(),
                service.getServiceName(),
                service.getShortDesc(),
                service.getLongDesc(),
                service.getPrice(),
                service.getCategory(),
                images
        );
    }
}

