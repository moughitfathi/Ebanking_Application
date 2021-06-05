package com.spring.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spring.ebanking.entities.Agence;
import com.spring.ebanking.entities.Banquier;
import com.spring.ebanking.entities.Client;
import com.spring.ebanking.services.AgenceService;


@RestController
public class AgenceController {
	AgenceService agenceservice;
	
	
	
	
	//recuperer   les agences 
	@GetMapping("/listeagences")
	public List<Agence> getAgences(@RequestParam(name="id", required=false) Long id) throws Exception
	{
		return agenceservice.getAgences(id);
	}

	//recuperer les banquiers d'une agence
	@GetMapping("/agence/{id}/banquiers")
	public List<Banquier> getBanquiers(@PathVariable(name="id") Long id) throws Exception
	{
		return agenceservice.getBanquiers(id);
	}
	
	//les clients enregistrés dans l'agence
	@GetMapping("/agence/{id}/clients")
	public List<Client> getClients(@PathVariable(name="id") Long id) throws Exception
	{
		return agenceservice.getClients(id);
	}

	
	
	//ajoputer une agence
	@PostMapping("/agences")
	public void addAgence(@RequestBody Agence agence)  throws  Exception
	{
		agenceservice.addAgence(agence);
	}
	
	//modifier une agence
	@PutMapping("/agence/{id}")
	public void updateAgence(@PathVariable Long id , @RequestBody(required=false) Agence agence)  throws Exception
	{
		agenceservice.updateAgence(id,agence);
	}


	


	//supprimer une agence
	@DeleteMapping("/agence/{id}")
	public void deleteAgence(@PathVariable Long id) throws Exception
	{
		agenceservice.removeAgence(id);
	}
	





}
