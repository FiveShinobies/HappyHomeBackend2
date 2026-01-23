package com.backend.happyhome.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.happyhome.entities.HouseholdService;

public interface HouseholdServiceRepo extends JpaRepository<HouseholdService, Long> {

}
