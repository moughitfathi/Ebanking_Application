package com.spring.ebanking.services;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
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
	
	@Autowired
	BanquierService banquierService;
	
	
	@Autowired
	AgenceService agenceService;
	
	
	@Autowired
	EmailService emailService;
	
	
			public void addClient(Client client) throws Exception{
				
				if(clientRepository.findByEmail(client.getEmail()).isPresent()) {
					throw new Exception("This email already exists try another one .");
					
				}
				if(clientRepository.findByCin(client.getCin()).isPresent()) {
					throw new Exception("This cin already exists try another one .");
				
				}
				if(clientRepository.findByTel(client.getTel()).isPresent()) {
					throw new Exception("This Tel already exists try another one .");
					
				}
				String password =client.getPassword() ;
				if(!password.isEmpty() && password!=null) {
					client.setPassword(new BCryptPasswordEncoder().encode(client.getPassword()));	
				}
				else {
					throw new Exception("password does not setted");
				}
				
				client.setDateInscription(new Date());
				 
				client.setRole(roleRepository.findByRole("client")
						.orElseThrow(()-> new NotFoundException("role not found")));
				
				Banquier banquier = banquierService.getByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
				
				client.setBanquier(banquier);
				
				Agence agence = agenceService.getAgences(banquier.getAgence().getId()).get(0);
				client.setAgence(agence);
				
				
				clientRepository.save(client);
				client.setPassword(null		);
				emailService.sendEmail(client);
			
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
	
	public void updateClient(Long id ,Client client) throws Exception {
		
		Client updateClient = clientRepository.findById(id)
				.orElseThrow(()-> new Exception("No customer with this id :"+id));
		
		if(clientRepository.findByEmail(client.getEmail()).isPresent() && !(clientRepository.findByEmail(client.getEmail()).get()==updateClient))
		throw new Exception("try with anothor email");
		
		if(clientRepository.findByCin(client.getCin()).isPresent() &&  !(clientRepository.findByCin(client.getCin()).get()==updateClient)                 )
		throw new Exception("try with anothor cin ");
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");  

		
		if(client.getPrenom()!=null && !client.getPrenom().isEmpty()) updateClient.setPrenom(client.getPrenom());
		if(client.getNom()!=null && !client.getNom().isEmpty()) updateClient.setNom(client.getNom());
		if(client.getAdresse()!=null && !client.getAdresse().isEmpty()) updateClient.setAdresse(client.getAdresse());
		if(client.getTel()!=null && !client.getTel().isEmpty()) updateClient.setTel(client.getTel());
		if(client.getPrenom()!=null && !client.getPrenom().isEmpty()) updateClient.setPrenom(client.getPrenom());
		if(client.getDateNaissance()!=null && !(dateFormat.format(client.getDateNaissance()).isEmpty())) updateClient.setDateNaissance(client.getDateNaissance());
		if(client.getEmail()!=null && !client.getEmail().isEmpty()) updateClient.setEmail(client.getEmail());
		if(client.getCin()!=null && !client.getCin().isEmpty()) updateClient.setCin(client.getCin());
		if(client.getPassword()!=null && !client.getPassword().isEmpty()) updateClient.setPassword(new BCryptPasswordEncoder().encode(client.getPassword()));
		
		clientRepository.save(updateClient);
		
		
		
		emailService.sendEmail(updateClient);

		
		
	}
	

	
	//delete Customer
	public void deleteClient(Long id) throws Exception{
		
		
		if(!clientRepository.findById(id).isPresent()) throw new NotFoundException("No customer with this id :"+id);
		clientRepository.deleteById(id);
		
		Banquier banquier = banquierService.getByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
		System.out.println("The customer with id : "+id+" was deleted by "+banquier);
	
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
