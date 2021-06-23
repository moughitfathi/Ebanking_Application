package com.spring.ebanking.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.ebanking.entities.Beneficiare;
import com.spring.ebanking.repositories.BeneficiareRepository;

import javassist.NotFoundException;


@Service
public class BeneficiareService {
		
		@Autowired
		BeneficiareRepository benrepo;	
		public Beneficiare getBeneficiare(Long id) throws NotFoundException {
				
				Beneficiare bene = benrepo.findById(id).orElseThrow(
						()-> new NotFoundException("le beneficiaire avec l'id = "+id+" est introuvable!"));
				
				return bene;
		}
		
		public void addBeneficiare(Beneficiare bene) throws Exception{
					//cherecher avec le numero du compte car il est unique 
			
				if(benrepo.findByNumeroDecompte(bene.getNumeroDecompte()).isPresent()) {
						throw new Exception("un Beneficiaire avec le nom "+bene.getNom()+"  existe deja");
				}else
				{
					benrepo.save(bene);
				}
		}
		
		
		public void deleteBeneficiare(Beneficiare bene) throws Exception{
			if (!benrepo.findByNumeroDecompte(bene.getNumeroDecompte()).isPresent()) {
				throw new Exception("le Beneficiaire dont le nom est "+bene.getNom()+" est introuvable !");
			}else {
				benrepo.delete(bene);
			}
			
		}
		
		public Optional<Beneficiare> getBeneficiaireByNumeroCompte(int num) {
			
			Optional<Beneficiare> beneficiare = benrepo.findByNumeroDecompte(num);
			
			return beneficiare;
			
			
		}
	
}
