package com.spring.ebanking.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
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
import com.spring.ebanking.entities.CreneauDispo;
import com.spring.ebanking.entities.RDV;
import com.spring.ebanking.services.BanquierService;

@RestController
public class BanquierController {
	@Autowired
	BanquierService banquierservice;
	

	//recuperer les banquiers
	@GetMapping("/banquiers")
	public List<Banquier> getBanquiers(@RequestParam(name="id", required=false) Long id) throws Exception
	{
		return  banquierservice.getBanquiers(id);
	}
	
	
	@GetMapping("/banquier/email/{email}")
	public Banquier getByEmail(@PathVariable(name="email") String email) throws Exception
	{
		return banquierservice.getByEmail(email);
	}
	
	
	
	//ajouter un banquier
	@PostMapping("/banquier")
	public void addBanquier(@RequestBody Banquier banquier)  throws Exception
	{
		banquierservice.addBanquier(banquier);
	}



    //modifier les infos d'un banquier
	@PutMapping("/banquier/{id}")
	public void updateBanquier(@PathVariable Long id , @RequestBody(required=false) Banquier banquier)  throws Exception
	{
		banquierservice.updateBanquier(id,banquier);
	}


	
    //supprimer un banquier
	@DeleteMapping("/banquier/{id}")
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
	public List<RDV> getRendezVous(@RequestBody Banquier banquier ){
		return banquier.getListeRendez_vous();
	}
	
	
}
