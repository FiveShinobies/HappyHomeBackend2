package com.backend.happyhome.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.happyhome.entities.Consumer;

public interface ConsumerRepo extends JpaRepository<Consumer, Long> {

}
