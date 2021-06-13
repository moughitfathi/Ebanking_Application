package com.spring.ebanking.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.spring.ebanking.entities.Personne;
import com.spring.ebanking.repositories.PersonneRepository;


@Service
public class UserDetailServiceImpl implements UserDetailsService {

	@Autowired
	PersonneRepository personneRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Personne personne=personneRepository.findByUsername(username).get();
		UserDetailmpl userD= new UserDetailmpl(personne);
		return userD;
		
	}
	
	
}
