package com.spring.ebanking.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.ebanking.entities.Banquier;

public interface BanquierRepository extends JpaRepository<Banquier, Long> {

	
	Optional<Banquier> findByEmail(String email);
}
