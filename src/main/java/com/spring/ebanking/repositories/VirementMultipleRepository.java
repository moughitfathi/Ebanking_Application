package com.spring.ebanking.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.ebanking.entities.VirementMultiple;

public interface VirementMultipleRepository extends JpaRepository<VirementMultiple, Long> {
	Optional<VirementMultiple> findById(Long id);

}
