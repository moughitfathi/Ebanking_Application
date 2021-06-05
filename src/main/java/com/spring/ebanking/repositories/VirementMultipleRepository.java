package com.spring.ebanking.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.ebanking.entities.Compte;
import com.spring.ebanking.entities.VirementMultiple;

public interface VirementMultipleRepository extends JpaRepository<VirementMultiple, Long> {
	

	Optional<VirementMultiple> findById(Long id);
	List<VirementMultiple> findAllByCompte(Compte compte);
}
