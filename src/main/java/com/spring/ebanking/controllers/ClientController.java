package com.spring.ebanking.controllers;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
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
import com.spring.ebanking.entities.ListVwithNumCmpte;
import com.spring.ebanking.entities.VirementMultiple;
import com.spring.ebanking.entities.VirementMulttipleBeneficiare;
import com.spring.ebanking.repositories.BeneficiareRepository;
import com.spring.ebanking.repositories.VirementMultipleBenificiareRepository;
import com.spring.ebanking.repositories.VirementMultipleRepository;
import com.spring.ebanking.services.BeneficiareService;
import com.spring.ebanking.services.ClientService;
import com.spring.ebanking.services.CompteService;
import com.spring.ebanking.services.VirementMultipleService;

import javassist.NotFoundException;
import kotlin.jvm.JvmStatic;

@RestController
@CrossOrigin(origins = "http://localhost:4200")

public class ClientController {
	
	@Autowired
	BeneficiareRepository benerepo;
	@Autowired
	ClientService clientService;

	@Autowired
	CompteService compteService;
	
	@Autowired
	VirementMultipleRepository virementrepo;
	
	@Autowired
	VirementMultipleService vmservice;

	@Autowired
	BeneficiareService beneficiaireService ;
	
	@Autowired
	VirementMultipleBenificiareRepository vms ;
	
	
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
	@GetMapping("/banquier/client/{id}/comptes")
	public List<Compte> getComptess(@PathVariable(name="id") Long id) throws NotFoundException{
		
		
		return clientService.getComptes(id);
		
	}
		@GetMapping("/client{id}/beneficiaires")
		public List<Beneficiare> getBeneficiaires(@PathVariable(name="id")Long id) throws NotFoundException{
			
			return clientService.getBeneficiaires(id);
		}
		
		//add client
		@PostMapping("/banquier/client")
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
		
		@GetMapping("/client/username/{u}")
		public Client getClientByUsername(@PathVariable String u) throws Exception {
			
			return clientService.getByUsername(u);
		}
		
		@GetMapping("/client/beneficiaire/{num}")
		public Beneficiare getBeneficiaireByNumeroCompte(@PathVariable int num) throws NotFoundException {
			
			 Compte compte= compteService.getByNumero(num);
			Client client=compte.getClient();
			Beneficiare beneficiaire=new Beneficiare();
			beneficiaire.setNom(client.getNom());
			beneficiaire.setPrenom(client.getPrenom());
			beneficiaire.setNumeroDecompte(num);
			
			benerepo.save(beneficiaire);
			return beneficiaire;
			
			
			
		}
		@PostMapping("client/effectuervirement/{id}")
		public void effectuervirement(@RequestBody List<ListVwithNumCmpte> list,@PathVariable Long id ) throws NotFoundException {
			Compte c=compteService.getCompte(id);
			Client cl=c.getClient();
			VirementMultiple vm=new VirementMultiple();
			BigDecimal sommeTotal = new BigDecimal(0) ;
			List<VirementMulttipleBeneficiare> liste1= new ArrayList<VirementMulttipleBeneficiare>();
			Beneficiare bene= new Beneficiare();
			for(ListVwithNumCmpte v:list) {
				VirementMulttipleBeneficiare vrmentbene= new VirementMulttipleBeneficiare();

				bene = getBeneficiaireByNumeroCompte(v.getNumCompte());
				bene.setClient(cl);
				vrmentbene.setBeneficiare(bene);
				vrmentbene.setMontant(v.getSolde());
				sommeTotal = sommeTotal.add(v.getSolde());
				
				liste1.add(vrmentbene); 
			}
			System.out.println(liste1);
			
			int nbrebene= liste1.size	();
			System.out.println(nbrebene);
			
			vm.setClient(cl);
			vm.setCompte(c);
			vm.setNombreDeBeneficiare(nbrebene);
			vm.setVirementMultipleBeneficiare(liste1);
			vm.setDateCreation(new Date());
			vm.setDateExecution(new Date());
			vm.setMentant(sommeTotal);
			
			
			vmservice.EffectuerVirmentmultiple(vm);
			
		}
		
}
