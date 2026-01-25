package com.backend.happyhome.repository.service_repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.happyhome.entities.ServiceImage;

public interface ServiceImageRepository extends JpaRepository<ServiceImage, Long> {

    List<ServiceImage> findByMyService_ServiceId(Long serviceId);

}
