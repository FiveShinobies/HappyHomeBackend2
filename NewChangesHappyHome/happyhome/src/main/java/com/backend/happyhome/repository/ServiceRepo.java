package com.backend.happyhome.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.happyhome.entities.HouseholdService;
import com.backend.happyhome.entities.enums.Category;

public interface ServiceRepo extends JpaRepository<HouseholdService, Long> {

	HouseholdService findByCategoryAndServiceName(Category category, String serviceName);

}
