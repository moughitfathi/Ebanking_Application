package com.spring.ebanking.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.ebanking.entities.Beneficiare;

public interface BeneficiareRepository extends JpaRepository<Beneficiare, Long> {

}
