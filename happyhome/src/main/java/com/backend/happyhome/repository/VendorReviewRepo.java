package com.backend.happyhome.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.backend.happyhome.entities.VendorReview;

@Repository
public interface VendorReviewRepo extends JpaRepository<VendorReview, Long> {

}
