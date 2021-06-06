package com.spring.ebanking.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.spring.ebanking.entities.Banquier;
import com.spring.ebanking.entities.Role;
import com.spring.ebanking.repositories.BanquierRepository;
import com.spring.ebanking.repositories.RoleRepository;

import javassist.NotFoundException;

@Service
public class BanquierService {
	
	@Autowired
	BanquierRepository banquierRepository;
	@Autowired
	RoleRepository roleRepository;
	
	public List<Banquier> getBanquiers(Long id)  throws Exception
	{
		
		List<Banquier> banquiers= new ArrayList<Banquier>();	
		if(id!=null)
			banquiers.add(banquierRepository.findById(id).orElseThrow(() -> new Exception("Aucun banquier avec l'id "+id+" trouvé")));
		
		else
			banquiers=banquierRepository.findAll();
		
		if(banquiers.isEmpty())  throw new Exception("Aucun agent trouvé");
		return banquiers;
	}
	
	
	
	public void addBanquier(Banquier banquier) throws Exception {
	    if(banquierRepository.findByEmail(banquier.getEmail()).isPresent()) {
			throw new Exception("Veuillez choisir un autre Email");
	    }
	   	
        banquier.setPassword(new BCryptPasswordEncoder().encode(banquier.getPassword()));
       // Role role=roleRepository.findByRole("banquier").orElseThrow(() -> new Exception("error role introuvable"));
			
	   // banquier.setRole(role);
    /*  set attribute  admin who add this banquier
		Admin admin=adminService.getById(SecurityContextHolder.getContext().getAuthentication().getName());
		banquier.setAdmin(admin);
    */
		 banquierRepository.save(banquier);
		 
	/*  on doit ajouter la fonction pour envoyer password par email au banquier
	*/

	}
	
	
	
	public void updateBanquier(Long id, Banquier banquier) throws Exception {
		
	// if banquier not exist throw exception
		Banquier banquier1= banquierRepository.findById(id).orElseThrow(() -> new Exception("aucun banquier avec cet Id "));
		
	//else
		if(banquier.getNom()!=null && !banquier.getNom().isEmpty()) banquier1.setNom(banquier.getNom());
		if(banquier.getPrenom()!=null && !banquier.getPrenom().isEmpty()) banquier1.setPrenom(banquier.getPrenom());
		if(banquier.getCin()!=null && !banquier.getCin().isEmpty()) banquier1.setCin(banquier.getCin());
		if(banquier.getTel()!=null && !banquier.getTel().isEmpty()) banquier1.setTel(banquier.getTel());
		if(banquier.getAdresse()!=null && !banquier.getAdresse().isEmpty()) banquier1.setAdresse(banquier.getAdresse());
		if(banquier.getEmail()!=null && !banquier.getEmail().isEmpty()) banquier1.setEmail(banquier.getEmail());
		if(banquier.getPassword()!=null && !banquier.getPassword().isEmpty()) banquier1.setPassword(new BCryptPasswordEncoder().encode(banquier.getPassword()));
	/*	if(banquier.getDateNaissance()!=null && !(banquier.getDateNaissance()).equals("")) banquier.setDateNaissance(banquier.getDateNaissance());
		if(banquier.getDateInscription()!=null && !banquier.getDateInscription().equals("")) banquier.setDateInscription(banquier.getDateNaissance());
	*/
		banquierRepository.save(banquier1);
		
		if(banquier.getPassword()!=null && !banquier.getPassword().isEmpty()) banquier1.setPassword(banquier.getPassword());
	/*
		else updated.setPassword(null);
		emailService.sendAuthenticationInfos(updated);
		
		Admin admin = adminService.getById(SecurityContextHolder.getContext().getAuthentication().getName());
		logger.debug("L'administrateur "+admin.getNom()+" "+admin.getPrenom()+" a modifié l'agent avec l'email "+updated.getEmail());
    */
		
	}
	
	public void deleteBanquier(Long id) throws Exception {
	//vérifier l'existence du banquier
	    Banquier banquier=banquierRepository.findById(id).orElseThrow(() -> new Exception(" banquier avec l'id "+id+" n'est pas trouvé"));
	    banquierRepository.delete(banquier);
	    
	/* Admin admin = adminService.getById(SecurityContextHolder.getContext().getAuthentication().getName());
	   logger.debug("L'administrateur "+admin.getNom()+" "+admin.getPrenom()+" a supprimé  le banquier "+banquier.getId());
	*/
	}
	
	public Banquier getByEmail(String email) throws NotFoundException{
		
		
		Banquier banquier = banquierRepository.findByEmail(email)
				.orElseThrow(()-> new NotFoundException("banker with this email"));
		
		return banquier;
		
		
	}
	
	

}
