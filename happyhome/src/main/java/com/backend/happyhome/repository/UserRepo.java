package com.backend.happyhome.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.backend.happyhome.entities.User;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {

	Optional<User> getByEmail(String email);
	Optional<User> getByPhone(String phone);

}
