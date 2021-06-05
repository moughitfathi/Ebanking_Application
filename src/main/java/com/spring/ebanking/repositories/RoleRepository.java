package com.spring.ebanking.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.ebanking.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{
	

	Optional<Role> findByRole(String role);

	Optional<Role>  findById(Long id);

}
