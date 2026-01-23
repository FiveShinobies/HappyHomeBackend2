package com.backend.happyhome.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.happyhome.entities.User;

public interface UserRepo extends JpaRepository<User, Long> {

}
