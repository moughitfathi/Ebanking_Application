package com.spring.ebanking.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.ebanking.entities.Admin;

public interface AdminRepository extends JpaRepository<Admin, Long> {
		
		Optional<Admin> findByEmail(String email); 
		Optional<Admin> findByCin(String cin);
}
