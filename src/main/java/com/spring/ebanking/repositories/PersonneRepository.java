package com.spring.ebanking.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.ebanking.entities.Personne;

public interface PersonneRepository extends JpaRepository<Personne, Long> {
		Optional<Personne> findByUsername(String username);
}
