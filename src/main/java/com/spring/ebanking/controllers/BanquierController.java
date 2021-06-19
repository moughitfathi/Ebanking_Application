package com.spring.ebanking.controllers;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.spring.ebanking.entities.Banquier;
import com.spring.ebanking.entities.Client;
import com.spring.ebanking.entities.CreneauDispo;
import com.spring.ebanking.entities.RDV;
import com.spring.ebanking.services.BanquierService;
import com.spring.ebanking.services.ClientService;

import javassist.NotFoundException;

@RestController
@CrossOrigin(origins = "http://localhost:4200")

public class BanquierController {
	@Autowired
	BanquierService banquierservice;
	@Autowired
	ClientService clientService;
	

	//recuperer les banquiers
	@GetMapping("/admin/banquiers/{id}")
	public List<Banquier> getBanquiers(@PathVariable(name="id", required=false) Long id) throws Exception
	{	
		return  banquierservice.getBanquiers(id);
	}
	
	
	@GetMapping("/banquier/username/{username}")
	public Banquier getByUsername(@PathVariable(name="username") String username) throws Exception
	{
		return banquierservice.getByUsername(username);
	}
	
	
	
	//ajouter un banquier
	@PostMapping("/admin/banquier")
	public void addBanquier(@RequestBody Banquier banquier)  throws Exception
	{
		banquierservice.addBanquier(banquier);
	}



    //modifier les infos d'un banquier
	@PutMapping("/admin/banquier/{id}")
	public void updateBanquier(@PathVariable Long id , @RequestBody(required=false) Banquier banquier)  throws Exception
	{
		banquierservice.updateBanquier(id,banquier);
	}


	
    //supprimer un banquier
	@DeleteMapping("/admin/banquier/{id}")
	public void deleteBanquier(@PathVariable Long id) throws Exception
	{
		banquierservice.deleteBanquier(id);
	}
	
	
	
	
	//set les creno dispo:
	@PostMapping("/banquier/addcreno")
	@ResponseStatus(HttpStatus.CREATED)
	public void addCreno(@RequestBody List<CreneauDispo> ListeCrean,@RequestBody Banquier banquier) {
		
		banquierservice.addCreneauDispo(ListeCrean, banquier);
	}
	@GetMapping("/banquier/rendez-vous")
	@ResponseStatus(HttpStatus.OK)
	public Collection<RDV> getRendezVous(@RequestBody Banquier banquier ){
		return banquier.getListeRendez_vous();
	}
	
	@PutMapping("/banquier/sus_act/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void sus_act(@PathVariable Long id) throws NotFoundException {
		
		Client c = clientService.getClient(id);
		banquierservice.Sus_Act_client(c);
	}
	
	
}
