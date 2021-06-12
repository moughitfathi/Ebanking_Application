package com.spring.ebanking.services;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.spring.ebanking.entities.Admin;
import com.spring.ebanking.entities.Banquier;
import com.spring.ebanking.entities.Client;
import com.spring.ebanking.entities.CreneauDispo;
import com.spring.ebanking.entities.Personne;
import com.spring.ebanking.entities.Role;
import com.spring.ebanking.repositories.BanquierRepository;
import com.spring.ebanking.repositories.CreneauDispoRepository;
import com.spring.ebanking.repositories.PersonneRepository;
import com.spring.ebanking.repositories.RoleRepository;


@Service
public class BanquierService {
	@Autowired
	PersonneRepository personneRepository;
	@Autowired
	BanquierRepository banquierRepository;
	@Autowired
	RoleRepository roleRepository;
	@Autowired
	CreneauDispoRepository creneauRepository;
	@Autowired
	AdminService adminService;
	@Autowired
	EmailService emailService;
	
	
	
	//retourner les banquiers ou un seul
	public List<Banquier> getBanquiers(Long id)  throws Exception
	{
		
		List<Banquier> banquiers= new ArrayList<Banquier>();	
		if(id!=null)
			banquiers.add(banquierRepository.findById(id).orElseThrow(() -> new Exception("Aucun banquier avec l'id "+id+" trouvé")));
		
		else
			banquiers=banquierRepository.findAll();
		
		if(banquiers.isEmpty())  throw new Exception("Aucun banquier trouvé");
		return banquiers;
	}
	//banquier par email
	public Banquier getByUsername(String username) throws Exception
	{
		Personne p= personneRepository.findByUsername(username).orElseThrow(() -> new Exception("Aucun banquier avec l'username "+username+" trouvé"));
		return banquierRepository.findById(p.getId()).orElseThrow(() -> new Exception("Aucun banquier avec l'username "+username+" trouvé"));
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
	     if(personneRepository.findByUsername(banquier.getUsername()).isPresent())
				throw new Exception("try with anothor Username ");
	   	
         banquier.setPassword(new BCryptPasswordEncoder().encode(banquier.getPassword()));
         Role role=roleRepository.findByRole("banquier").orElseThrow(() -> new Exception("error role introuvable"));
		 banquier.setDateInscription(new Date());	
	     banquier.setRole(role);
    // set attribute  admin who add this banquier
	 	 Admin admin=adminService.getByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
         banquier.setAdmin(admin);
    
		 banquierRepository.save(banquier);
         emailService.sendEmail(banquier);

	}
	
	
	//modifier lesinfos du banquier avec l' Id 
	public void updateBanquier(Long id, Banquier banquier) throws Exception {
		// if banquier not exist throw exception
		
		
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
		DateFormat date=new SimpleDateFormat("yyyy-mm-dd");
		
		if(personneRepository.findByUsername(banquier.getUsername()).isPresent() && !(personneRepository.findByUsername(banquier.getUsername()).get().equals(banquier1.getUsername())))
			throw new Exception("try with anothor Username ");
		
		
	//else
		if(banquier.getUsername()!=null && !banquier.getUsername().isEmpty()) banquier1.setUsername(banquier.getUsername());
		if(banquier.getNom()!=null && !banquier.getNom().isEmpty()) banquier1.setNom(banquier.getNom());
		if(banquier.getPrenom()!=null && !banquier.getPrenom().isEmpty()) banquier1.setPrenom(banquier.getPrenom());
		if(banquier.getCin()!=null && !banquier.getCin().isEmpty()) banquier1.setCin(banquier.getCin());
		if(banquier.getTel()!=null && !banquier.getTel().isEmpty()) banquier1.setTel(banquier.getTel());
		if(banquier.getAdresse()!=null && !banquier.getAdresse().isEmpty()) banquier1.setAdresse(banquier.getAdresse());
		if(banquier.getEmail()!=null && !banquier.getEmail().isEmpty()) banquier1.setEmail(banquier.getEmail());
		if(banquier.getPassword()!=null && !banquier.getPassword().isEmpty()) banquier1.setPassword(new BCryptPasswordEncoder().encode(banquier.getPassword()));
		if(banquier.getDateNaissance()!=null && !(date.format(banquier.getDateNaissance())).isEmpty()) banquier.setDateNaissance(banquier.getDateNaissance());
		
		banquierRepository.save(banquier1);
		if(banquier.getPassword()!=null && !banquier.getPassword().isEmpty()) banquier1.setPassword(banquier.getPassword());
		else banquier1.setPassword(null);
		emailService.sendEmail(banquier1);
		
		
	
    
		
	}
	
	
	//supprimer un banquier 
	public void deleteBanquier(Long id) throws Exception {
	//vérifier l'existence d'un banquier
	  
		Banquier banquier=banquierRepository.findById(id).orElseThrow(() -> new Exception(" banquier avec l'id "+id+" n'est pas trouvé"));
	    banquierRepository.delete(banquier);
	    
	
	}
	
	
	
	
	//ajouter les creneau disponibles
	public void addCreneauDispo(List<CreneauDispo> creneaudispos, Banquier banquier) {
		
		List<CreneauDispo> creneauxlocale=new ArrayList<CreneauDispo>();
		CreneauDispo  creneaudispo=new CreneauDispo();
		
		DateFormat dateformat=new SimpleDateFormat("yyyy-mm-dd");
		for (CreneauDispo i : creneaudispos) {
			if(i.getDateDebut()!=null && !(dateformat.format(i.getDateDebut()).isEmpty())) creneaudispo.setDateDebut(i.getDateDebut());
			if(i.getDateFin()!=null && !(dateformat.format(i.getDateFin()).isEmpty()))  creneaudispo.setDateFin(i.getDateFin());
		
			creneauxlocale.add(creneaudispo);
			creneauRepository.save(creneaudispo);
			}
 
		banquier.setListeCreneauDispos(creneauxlocale);
		banquierRepository.save(banquier);
		
	}
	
	// methode to activate or deactivate client
	public void Sus_Act_client(Client client) {
		if(client.isActive()) client.setActive(false);
		else client.setActive(true);
	}
	
	
	

}
