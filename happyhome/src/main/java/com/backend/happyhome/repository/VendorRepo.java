package com.backend.happyhome.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.happyhome.entities.Vendor;

public interface VendorRepo extends JpaRepository<Vendor, Long> {

}
