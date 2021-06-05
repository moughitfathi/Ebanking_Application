package com.spring.ebanking.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.ebanking.entities.Beneficiare;

public interface BeneficiareRepository extends JpaRepository<Beneficiare, Long> {
	Optional<Beneficiare> findByNumeroDecompte(int numeroDecompte);
	
}
