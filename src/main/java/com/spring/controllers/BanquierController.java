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

import com.spring.ebanking.entities.Banquier;
import com.spring.ebanking.services.BanquierService;

@RestController
public class BanquierController {
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
	
}
