package com.spring.ebanking.services;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.spring.ebanking.entities.Admin;
import com.spring.ebanking.entities.Agence;
import com.spring.ebanking.entities.Banquier;
import com.spring.ebanking.entities.Client;
import com.spring.ebanking.repositories.AgenceRepository;

@Service
public class AgenceService {
	
	@Autowired
	AgenceRepository agenceRepo;
	@Autowired
	AdminService adminService;
	Logger logger = LoggerFactory.getLogger(AgenceService.class.getName());

	
	//recuperer une agence ou plusieurs
	public List<Agence> getAgences(Long id)  throws Exception
	{
		
		List<Agence> agences= new ArrayList<Agence>();	
		if(id!=null)
			agences.add(agenceRepo.findById(id).orElseThrow(() -> new Exception("Aucune agence avec l'id "+id+" trouvée")));
		
		else
			agences=agenceRepo.findAll();
		
		if(agences.isEmpty())  throw new Exception("Aucune agence trouvée");
		return agences;
	}
	
	
	// recuperer les banquiers d'une agence
	public List<Banquier> getBanquiers(Long id) throws Exception
	{
		Agence agence= agenceRepo.findById(id).orElseThrow(() -> new Exception("Aucune agence avec l'id "+id+" trouvée"));
		if(agence.getListeBanquiers().isEmpty()) throw new Exception("Cet agence ne contient aucun agent.");
		return agence.getListeBanquiers();
	}
	
	
	
	//recuperer la liste des clients dans une agence
	public List<Client> getClients(Long id)  throws Exception
	{
		
		Agence agence= agenceRepo.findById(id).orElseThrow(() -> new Exception("Aucune agence avec l'id "+id+" trouvée"));
		if(agence.getListeClients().isEmpty()) throw new Exception("Cet agence ne contient aucun client.");
		return agence.getListeClients();
	}
	
	
	//ajouter une agence
	public void addAgence(Agence agence) throws  Exception
	{
		if(agenceRepo.findByNom(agence.getNom()).isPresent()) {
			throw new Exception("Une agence avec le Nom "+agence.getNom()+" existe déjà");
		}
		if(agenceRepo.findByEmail(agence.getEmail()).isPresent()) {
			throw new Exception("Une agence avec le Email"+agence.getEmail()+" existe déjà");
		}if(agenceRepo.findByTel(agence.getTel()).isPresent()) {
			throw new Exception("Une agence avec le Tel"+agence.getTel()+" existe déjà");
		}
		agenceRepo.save(agence);	
		Admin admin = adminService.getByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
		agence.setAdmin(admin);	
		logger.debug("L'administrateur "+admin.getNom()+" "+admin.getPrenom()+"  a créé l'agence "+agence.getNom());
 	}
	
	//delete agence
	public void removeAgence(Long id) throws Exception
	{
		
		//vérifier l'existence de l'agence
		Agence agence=agenceRepo.findById(id).orElseThrow(() -> new Exception("Aucune agence avec l'id "+id+" n'est trouvé"));
		agenceRepo.delete(agence);
		
		Admin admin = adminService.getByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
		logger.debug("L'administrateur "+admin.getNom()+" "+admin.getPrenom()+"a supprimé l'agence"+agence.getNom());
    
	}
	
	
	//modifier les informatios d'une agence
	public void updateAgence(Long id,Agence agence) throws Exception
	{
		Agence updated = agenceRepo.findById(id).orElseThrow(() -> new Exception("Aucune agence avec l'id "+id+" trouvé"));
		
		//verifier l'unicité du nouveau nom
		if(agenceRepo.findByNom(agence.getNom()).isPresent() && !(agenceRepo.findByNom(agence.getNom()).get()==updated))
			throw new Exception("Une agence avec le Nom "+agence.getNom()+" existe déjà");
		
		if(agenceRepo.findByEmail(agence.getEmail()).isPresent() && !(agenceRepo.findByEmail(agence.getTel()).get()==updated))
			throw new Exception("Une agence avec le Telephone "+agence.getEmail()+" existe déjà");
		
		if(agenceRepo.findByTel(agence.getTel()).isPresent() && !(agenceRepo.findByTel(agence.getTel()).get()==updated))
			throw new Exception("Une agence avec le Telephone "+agence.getTel()+" existe déjà");
		
		
		if(agence.getNom()!=null && !agence.getNom().isEmpty()) 	updated.setNom(agence.getNom());
		if(agence.getVille()!=null && !agence.getVille().isEmpty()) 	updated.setVille(agence.getVille());
		if(agence.getTel()!=null && !agence.getTel().isEmpty()) 	updated.setTel(agence.getTel());
		if(agence.getAdresse()!=null && !agence.getAdresse().isEmpty()) 	updated.setAdresse(agence.getAdresse());
		if(agence.getEmail()!=null && !agence.getEmail().isEmpty()) 	updated.setEmail(agence.getEmail());
		
		agenceRepo.save(updated);
		
		Admin admin = adminService.getByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
		logger.debug("L'administrateur "+admin.getNom()+" "+admin.getPrenom()+" a modifié l'agence "+updated.getNom());
	
	}

}




