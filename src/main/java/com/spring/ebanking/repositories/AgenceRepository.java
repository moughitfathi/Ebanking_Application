package com.spring.ebanking.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.ebanking.entities.Agence;

public interface AgenceRepository extends JpaRepository<Agence,Long> {
	
	Optional<Agence> findByNom(String nom);
	
	Optional<Agence> findByEmail(String email);
	
	Optional<Agence> findByTel(String tel);

}
