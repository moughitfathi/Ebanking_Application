package com.spring.ebanking.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.ebanking.entities.Client;

public interface ClientRepository extends JpaRepository<Client, Long>{

	Optional<Client> findById(Long id);
	Optional<Client> findByEmail(String email);
	Optional<Client> findByCin(String cin);
	Optional<Client> findByTel(String tel);
}
