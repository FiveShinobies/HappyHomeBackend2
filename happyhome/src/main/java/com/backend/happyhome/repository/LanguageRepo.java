package com.backend.happyhome.repository;

import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.happyhome.entities.Language;

public interface LanguageRepo extends JpaRepository<Language, Long> {

	Set<Language> findByUsersUserId(Long userId);
	Optional<Language> findByLangName(String langName);
}
