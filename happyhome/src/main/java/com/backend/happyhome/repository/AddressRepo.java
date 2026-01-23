package com.backend.happyhome.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.happyhome.entities.Address;

public interface AddressRepo extends JpaRepository<Address, Long> {

	List<Address> findByMyUserUserId(Long uid);
	
}
