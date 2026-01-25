package com.backend.happyhome.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.backend.happyhome.entities.Consumer;

@Repository
public interface ConsumerRepo extends JpaRepository<Consumer, Long> {

	Consumer findByMyUser_UserId(Long userId);

}
