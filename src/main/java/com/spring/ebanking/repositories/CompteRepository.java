package com.spring.ebanking.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.ebanking.entities.Compte;

public interface CompteRepository extends JpaRepository<Compte, Long>{
	
	public Optional<Compte> findById(Long id);
	public Optional<Compte> findByNumero(int numCompte);

}
