package com.spring.ebanking.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.spring.ebanking.entities.Agence;
import com.spring.ebanking.entities.Banquier;
import com.spring.ebanking.entities.Beneficiare;
import com.spring.ebanking.entities.Client;
import com.spring.ebanking.entities.Compte;
import com.spring.ebanking.entities.CreneauDispo;
import com.spring.ebanking.entities.RDV;
import com.spring.ebanking.entities.Role;
import com.spring.ebanking.repositories.ClientRepository;
import com.spring.ebanking.repositories.RoleRepository;

import javassist.NotFoundException;

public class ClientService {
	
	
	@Autowired
	ClientRepository clientRepository;
	@Autowired
	AgenceService agenceservice;
	
	@Autowired
	EmailService emailservice;
	
	@Autowired
	RoleRepository roleRepository;
	
	
			public void addClient(Client client) throws Exception{
				
				if(clientRepository.findByEmail(client.getEmail()).isPresent()) {
					throw new Exception("This email already exists try another one .");
					
				}
				if(clientRepository.findByCin(client.getCin()).isPresent()) {
					throw new Exception("This cin already exists try another one .");
				
				}
				
				client.setPassword(new BCryptPasswordEncoder().encode(client.getPassword()));
				 
				client.setRole(roleRepository.findByRole("Client")
						.orElseThrow(()-> new NotFoundException("role not found")));
				
				//Banquier banquier = 
				
					
				
				
				
			
			}
			
	public Client getClient(Long id) throws NotFoundException{
		
		Client client =clientRepository.findById(id)
				.orElseThrow(() -> new NotFoundException(" No client with this id :"+id));
	
			return client;	
				
	}
	
	public List<Client> getClients() throws NotFoundException{
		List<Client> clients= clientRepository.findAll();
		if(clients.isEmpty()) throw new NotFoundException("No client found");
		
		return clients;
		
	}
	//list of account of customer
	public List<Compte> getComptes(Long id) throws NotFoundException{
		
		Client client = clientRepository.findById(id)
				.orElseThrow(()-> new NotFoundException("No customer with this id :"+id));
		if(client.getComptes().isEmpty()) throw new NotFoundException("No account for this customer");
		
		
		return client.getComptes();
		
		
	}
	
	public List<Beneficiare> getBeneficiaires(Long id) throws NotFoundException{
		
		Client client =clientRepository.findById(id)
				.orElseThrow(()-> new NotFoundException("No customer with this id :"+id));
		
	if(client.getBeneficiaires().isEmpty()) throw new NotFoundException("This cutomer does not have any beneficiary ");
	
	return client.getBeneficiaires();
	
	}
	
	
	//delete Customer
	public void deleteClient(Long id) throws NotFoundException{
		
		
		if(!clientRepository.findById(id).isPresent()) throw new NotFoundException("No customer with this id :"+id);
		clientRepository.deleteById(id);
	
	}
	
	public List<CreneauDispo> getDateDispos(Client client) throws Exception {
		List<CreneauDispo> disponibles=new ArrayList<CreneauDispo>();
		 List<CreneauDispo> listecre =client.getBanquier().getListeCreneauDispos();
		  for(CreneauDispo d: listecre) {
			  if(d.getStatus()==false) {
				  disponibles.add(d);
			  }
		  }
		 return disponibles;
		}
	
	public void choixRDV(Client client,CreneauDispo creneaudispo) {

		Banquier banquier=creneaudispo.getBanquier();
		RDV objectrdv=new RDV();
		creneaudispo.setStatus(true);
		objectrdv.setDateRequette(creneaudispo.getDateDebut());
		client.getRendezVous().add(objectrdv);
		banquier.getListeRendez_vous().add(objectrdv);
		emailservice.sendConfirmationRendez_vous(banquier, client, creneaudispo);
		emailservice.sendConfirmationRendez_vous(client,banquier, creneaudispo);

		
		
		
		
	}
		
	}
