package com.spring.ebanking.services;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.spring.ebanking.entities.Admin;
import com.spring.ebanking.entities.Banquier;
import com.spring.ebanking.entities.Role;
import com.spring.ebanking.repositories.BanquierRepository;
import com.spring.ebanking.repositories.RoleRepository;


@Service
public class BanquierService {
	
	@Autowired
	BanquierRepository banquierRepository;
	@Autowired
	RoleRepository roleRepository;
	@Autowired
	AdminService adminService;
	@Autowired
	EmailService emailService;
	
	
	Logger logger = LoggerFactory.getLogger(BanquierService.class.getName());
	
	//retourner les banquiers ou un seul
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
	//banquier par email
	public Banquier getByEmail(String email) throws Exception
	{
		return banquierRepository.findByEmail(email).orElseThrow(() -> new Exception("Aucun banquier avec l'email "+email+" trouvé"));
	}
	
	
	//ajouter un banquier
	public void addBanquier(Banquier banquier) throws Exception {
	     if(banquierRepository.findByEmail(banquier.getEmail()).isPresent()) {
			throw new Exception("Veuillez choisir un autre Email");
	     }
	     if(banquierRepository.findByCin(banquier.getCin()).isPresent()) {
			throw new Exception("Veuillez choisir un autre cin");
	     }
	     if(banquierRepository.findByTel(banquier.getTel()).isPresent()) {
			throw new Exception("Veuillez choisir un autre numero de telephone");
	     }
	   	
         banquier.setPassword(new BCryptPasswordEncoder().encode(banquier.getPassword()));
         Role role=roleRepository.findByRole("banquier").orElseThrow(() -> new Exception("error role introuvable"));
			
	     banquier.setRole(role);
    // set attribute  admin who add this banquier
	 	 Admin admin=adminService.getByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
         banquier.setAdmin(admin);
    
		 banquierRepository.save(banquier);
         emailService.sendEmail(banquier);

	}
	
	
	//modifier lesinfos du banquier avec l' Id 
	public void updateBanquier(Long id, Banquier banquier) throws Exception {
		Banquier banquier1= banquierRepository.findById(id).orElseThrow(() -> new Exception("aucun banquier avec cet Id "));

		if(banquierRepository.findByEmail(banquier.getEmail()).isPresent()  && !(banquierRepository.findByEmail(banquier.getEmail()).get().getEmail().equals(banquier1.getEmail()))) {
			throw new Exception("Veuillez choisir un autre Email");
		}
		if(banquierRepository.findByCin(banquier.getCin()).isPresent() && !(banquierRepository.findByCin(banquier.getCin()).get().getCin().equals(banquier1.getCin()))) {
			throw new Exception("Veuillez choisir un autre cin");
		}
		if(banquierRepository.findByTel(banquier.getTel()).isPresent() && !(banquierRepository.findByTel(banquier.getTel()).get().getTel().equals(banquier1.getTel()))) {
			throw new Exception("Veuillez choisir un autre numero du telephone");
		}
	// if banquier not exist throw exception
		
	//else
		if(banquier.getNom()!=null && !banquier.getNom().isEmpty()) banquier1.setNom(banquier.getNom());
		if(banquier.getPrenom()!=null && !banquier.getPrenom().isEmpty()) banquier1.setPrenom(banquier.getPrenom());
		if(banquier.getCin()!=null && !banquier.getCin().isEmpty()) banquier1.setCin(banquier.getCin());
		if(banquier.getTel()!=null && !banquier.getTel().isEmpty()) banquier1.setTel(banquier.getTel());
		if(banquier.getAdresse()!=null && !banquier.getAdresse().isEmpty()) banquier1.setAdresse(banquier.getAdresse());
		if(banquier.getEmail()!=null && !banquier.getEmail().isEmpty()) banquier1.setEmail(banquier.getEmail());
		if(banquier.getPassword()!=null && !banquier.getPassword().isEmpty()) banquier1.setPassword(new BCryptPasswordEncoder().encode(banquier.getPassword()));
		if(banquier.getDateNaissance()!=null && !(banquier.getDateNaissance()).equals("")) banquier.setDateNaissance(banquier.getDateNaissance());
		if(banquier.getDateInscription()!=null && !banquier.getDateInscription().equals("")) banquier.setDateInscription(banquier.getDateNaissance());
	
		banquierRepository.save(banquier1);
		
		if(banquier.getPassword()!=null && !banquier.getPassword().isEmpty()) banquier1.setPassword(banquier.getPassword());
	
		else banquier1.setPassword(null);
		emailService.sendEmail(banquier1);
		
		Admin admin = adminService.getByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
		logger.debug("L'administrateur "+admin.getNom()+" "+admin.getPrenom()+" a modifié le banquier avec l'email "+banquier1.getEmail()+"");
    
		
	}
	
	
	//supprimer un banquier 
	public void deleteBanquier(Long id) throws Exception {
	//vérifier l'existence d'un banquier
	    Banquier banquier=banquierRepository.findById(id).orElseThrow(() -> new Exception(" banquier avec l'id "+id+" n'est pas trouvé"));
	    banquierRepository.delete(banquier);
	    
	    Admin admin = adminService.getByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
	    logger.debug("L'administrateur "+admin.getNom()+" "+admin.getPrenom()+" a supprimé  le banquier "+banquier.getId());
	
	}
	
	

}
