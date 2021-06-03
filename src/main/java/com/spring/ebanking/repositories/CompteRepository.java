package com.spring.ebanking.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.ebanking.entities.Compte;

public interface CompteRepository extends JpaRepository<Compte, Long>{

}
