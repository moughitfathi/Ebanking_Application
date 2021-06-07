package com.spring.ebanking.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.ebanking.entities.Compte;
import com.spring.ebanking.entities.VirementMultiple;
import com.spring.ebanking.repositories.CompteRepository;
import com.spring.ebanking.repositories.VirementMultipleRepository;
import java.util.*;

import javassist.NotFoundException;

@Service
public class CompteService {
	
	@Autowired
	CompteRepository compteRepository;

	@Autowired
	VirementMultipleRepository virementMultipleRepository;


	
	//get account
	public Compte getCompte(Long id) throws NotFoundException {
		
				Compte compte = compteRepository.findById(id)
						.orElseThrow(()->new NotFoundException("there is no account with this id :"+id));
				
						return compte;
	}
	
	
	//get a list of accounts
	public List<Compte> getComptes() throws NotFoundException{
				List<Compte> comptes =compteRepository.findAll();
				if(comptes.isEmpty()) throw new NotFoundException("No account found");
				
				return comptes;
		
	}
	
	//get account by his number
	public Compte getByNumero(int numCompte ) throws NotFoundException {
		
					Compte compte = compteRepository.findByNumero(numCompte)
							.orElseThrow(()-> new NotFoundException("Invalid account"));
									
				return compte;
	}
	
	//add account
	public void addCompte(Compte compte) throws Exception{
		
		
			if(compteRepository.findByNumero(compte.getNumero()).isPresent()) {
				throw new Exception("there is an account with this number :"+compte.getNumero());
			}
			
			
			compteRepository.save(compte);
	}
	
	public void updateCompte(Long id ,Compte compte) throws NotFoundException,Exception {
		
		
		Compte updateCompte = compteRepository.findById(id)
				.orElseThrow(()-> new NotFoundException("No account with this id :"+id));
		
		if(compteRepository.findByNumero(compte.getNumero()).isPresent() && !(compteRepository.findByNumero(compte.getNumero()).get()==updateCompte))
			throw new Exception("account with this num "+compte.getNumero()+"already exists");
		
		Integer compteNum = Integer.valueOf(compte.getNumero());
		if(compteNum!=null && !String.valueOf(compte.getNumero()).isEmpty()) updateCompte.setNumero(compte.getNumero());
		if(compte.getSolde()!=null && !String.valueOf(compte.getSolde()).isEmpty()) updateCompte.setSolde(compte.getSolde());
		
		
		compteRepository.save(updateCompte);

		
		
	}
	
	//delete account
	public void deleteCompte(Long id) throws NotFoundException {
				
				if(!compteRepository.findById(id).isPresent()) throw new NotFoundException("No account with this id :"+id);
				compteRepository.deleteById(id);
	}
	
	
	
	public List<VirementMultiple> virementMultiples(Long id) throws NotFoundException{
		Compte compte =compteRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("No account with this id :"+id));
		
				List<VirementMultiple> virementMultiples = virementMultipleRepository.findAllByCompte(compte);
		
				if(virementMultiples.isEmpty()) throw new NotFoundException("No transfer multiple was made ");
	
				return virementMultiples;
	
	}
	

}
