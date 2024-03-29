package com.spring.ebanking.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.ebanking.entities.Role;
import com.spring.ebanking.repositories.RoleRepository;

import javassist.NotFoundException;


@Service
public class RoleService {
	
	
	@Autowired
	RoleRepository roleRepository;
	
	public Role getRole(Long id) throws NotFoundException {
		
		Role role =roleRepository.findById(id)
				.orElseThrow(()-> new NotFoundException("No role found"));
		
		return role ;
		
		
	}

}
