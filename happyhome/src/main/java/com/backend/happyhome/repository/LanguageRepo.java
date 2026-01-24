package com.backend.happyhome.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.happyhome.entities.Language;

public interface LanguageRepo extends JpaRepository<Language, Long> {

	List<Language> findByUsersUserId(Long userId);
	
}
