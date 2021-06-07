package com.spring.ebanking.controllers;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.ebanking.entities.Beneficiare;
import com.spring.ebanking.entities.Client;
import com.spring.ebanking.entities.Compte;
import com.spring.ebanking.services.ClientService;

import javassist.NotFoundException;

@RestController
public class ClientController {
	
	ClientService clientService;
	
	
	@GetMapping("/client{id}")
	public Client getClient(@PathVariable(name="id")Long id) throws NotFoundException {
		
		return clientService.getClient(id);
	}
	
	@GetMapping("/clients")
	public List<Client> getClients() throws NotFoundException{
		
	
		return clientService.getClients();
	}
	
	@GetMapping("/client{id}/comptes")
	public List<Compte> getComptes(@PathVariable(name="id") Long id) throws NotFoundException{
		
		
		return clientService.getComptes(id);
		
	}
		@GetMapping("/client{id}/beneficiaires")
		public List<Beneficiare> getBeneficiaires(@PathVariable(name="id")Long id) throws NotFoundException{
			
			return clientService.getBeneficiaires(id);
		}
		
		//add client
		@PostMapping("/clients")
		public void  addClient(@RequestBody Client client) throws Exception {
			
			
			 clientService.addClient(client);
		}
		
		

		@PutMapping("/client{id}")
		public void updateClient(@PathVariable Long id ,@RequestBody Client client) throws Exception {
			
			
			clientService.updateClient(id, client);
		}
	
		
		@DeleteMapping("/client{id}")
	public void deleteClient(@PathVariable Long id) throws Exception {
		
		clientService.deleteClient(id);
	}
	

}
