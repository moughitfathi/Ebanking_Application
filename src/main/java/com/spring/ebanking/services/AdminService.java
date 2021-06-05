package com.spring.ebanking.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.spring.ebanking.entities.Admin;
import com.spring.ebanking.repositories.AdminRepository;
import com.spring.ebanking.repositories.RoleRepository;

import javassist.NotFoundException;

public class AdminService {
@Autowired 
AdminRepository adminRepository;
@Autowired
EmailService emailService;
@Autowired
RoleRepository roleRepository;


public Admin getByEmail(String email) throws Exception{
	Admin admin = adminRepository.findByEmail(email).orElseThrow(
			()->  new Exception("Admin with email "+email+" not found!"));
	return admin;
}

public Admin getById(Long id)throws NotFoundException {
		Admin admin = adminRepository.findById(id).orElseThrow(()->
			new NotFoundException("aucun Administratuer avec l'id = "+id+"est trouve")
				);
		return admin;
}

public List<Admin> getAdmins() throws NotFoundException{
	List<Admin> admins = new ArrayList<Admin>();
	admins=adminRepository.findAll();
	if(admins.isEmpty()) throw new NotFoundException("aucun administrateur est trouve");
	return admins;

} 

public void addAdmin(Admin admin)throws Exception {
	if(adminRepository.findByEmail(admin.getEmail()).isPresent()) {
			throw new Exception("l'administrateur est deja existent erreur Email! ");
	}
	if(adminRepository.findByCin(admin.getCin()).isPresent()) {
		throw new Exception("l'administrateur est deja existent erreur CIN");
	}
	
	admin.setPassword(new BCryptPasswordEncoder().encode(admin.getPassword()));
	admin.setRole(roleRepository.findByRole("admin").orElseThrow(
			()-> new Exception("le role dont le no est admin est introuvable!")));
	
	adminRepository.save(admin);
	emailService.sendEmail(admin);
	
}

	
	
}
