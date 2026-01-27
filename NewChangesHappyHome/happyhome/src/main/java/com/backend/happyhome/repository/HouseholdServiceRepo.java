package com.backend.happyhome.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.happyhome.entities.HouseholdService;

public interface HouseholdServiceRepo extends JpaRepository<HouseholdService, Long> {

	List<HouseholdService> findByActiveTrue();
	
	Optional<HouseholdService> findByServiceIdAndActiveTrue(Long serviceId);
}
