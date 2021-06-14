package com.spring.ebanking.controllers;

import java.util.Collection;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.spring.ebanking.entities.Beneficiare;
import com.spring.ebanking.entities.Client;
import com.spring.ebanking.entities.Compte;
import com.spring.ebanking.entities.CreneauDispo;
import com.spring.ebanking.services.ClientService;

import javassist.NotFoundException;

@RestController
public class ClientController {
	
	
	@Autowired
	ClientService clientService;
	
	
	@GetMapping("/banquier/client/{id}")
	public Client getClient(@PathVariable(name="id")Long id) throws NotFoundException {
		
		return clientService.getClient(id);
	}
	
	@GetMapping("/banquier/clients")
	public Collection<Client> getClients() throws NotFoundException{
		
	
		return clientService.getClients();
	}
	
	@GetMapping("/client/{id}/comptes")
	public List<Compte> getComptes(@PathVariable(name="id") Long id) throws NotFoundException{
		
		
		return clientService.getComptes(id);
		
	}
		@GetMapping("/client{id}/beneficiaires")
		public List<Beneficiare> getBeneficiaires(@PathVariable(name="id")Long id) throws NotFoundException{
			
			return clientService.getBeneficiaires(id);
		}
		
		//add client
		@PostMapping("/banquier/clients")
		public void  addClient(@RequestBody Client client) throws Exception {
			
			
			 clientService.addClient(client);
		}
		
		

		@PutMapping("/banquier/client/{id}")
		public void updateClient(@PathVariable Long id ,@RequestBody Client client) throws Exception {
			
			
			clientService.updateClient(id, client);
		}
	
		
		@DeleteMapping("/banquier/client/{id}")
	public void deleteClient(@PathVariable Long id) throws Exception {
		
		clientService.deleteClient(id);
	}
	
		@GetMapping("/client/creneaudisp")
		@ResponseStatus(HttpStatus.OK)
		
		public void getCreneauDispo(@RequestBody Client client) throws Exception {
			clientService.getDateDispos(client);
		}
		
		
		@PostMapping("/client/choixrdv")
		@ResponseStatus(HttpStatus.CREATED)
		public void choixrdv(@RequestBody Client client,@RequestBody CreneauDispo creneauDispo ) {
			clientService.choixRDV(client, creneauDispo);
		}
		
		@GetMapping("/client/username/{username}")
		public Client getClientByUsername(String username) throws Exception {
			
			return clientService.getByUsername(username);
		}
		
}
