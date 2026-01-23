package com.backend.happyhome.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.happyhome.entities.Language;

public interface LanguageRepo extends JpaRepository<Language, Long> {

}
