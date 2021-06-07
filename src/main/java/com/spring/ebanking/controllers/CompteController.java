package com.spring.ebanking.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.spring.ebanking.entities.Compte;
import com.spring.ebanking.entities.VirementMultiple;
import com.spring.ebanking.services.CompteService;

import javassist.NotFoundException;

@RestController
public class CompteController {
	
	CompteService compteService ;

	
		@GetMapping("/compte{id}")
		public Compte getCompte(@PathVariable(name="id")Long id) throws NotFoundException {
			
			return compteService.getCompte(id);
			
			
		}
		
		@GetMapping("/comptes")
		public List<Compte> getComptes() throws NotFoundException{
			
			return compteService.getComptes();
			
			
		}
		
		@GetMapping("/compte{num}")
		public Compte getByNumero(@PathVariable(name="num")int numCompte) throws NotFoundException {
			
			return compteService.getByNumero(numCompte);
		}
		
		
		@PostMapping("/comptes")
		public void addCompte(@RequestBody Compte compte) throws Exception {
			
			compteService.addCompte(compte);
		}
		
		@PutMapping("/compte{id}")
		public void updateCompte(@PathVariable Long id ,@RequestBody(required = false )Compte compte) throws NotFoundException, Exception {
			
			
			compteService.updateCompte(id, compte);
		}
		
		
		@DeleteMapping("/compte{id}")
		public void deleteCompte(@PathVariable Long id) throws NotFoundException {
			
			compteService.deleteCompte(id);
		}
		
		@GetMapping("/compte{id}/virementmultiple")
		List<VirementMultiple> getVirementMultiple(Long id) throws NotFoundException{
			
			return compteService.virementMultiples(id);
		}
		
		
}
