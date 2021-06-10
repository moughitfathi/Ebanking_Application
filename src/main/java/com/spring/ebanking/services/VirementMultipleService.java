package com.spring.ebanking.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

import com.spring.ebanking.entities.Beneficiare;
import com.spring.ebanking.entities.VirementMultiple;
import com.spring.ebanking.entities.VirementMulttipleBeneficiare;
import com.spring.ebanking.repositories.BeneficiareRepository;
import com.spring.ebanking.repositories.VirementMultipleBenificiareRepository;
import com.spring.ebanking.repositories.VirementMultipleRepository;

import javassist.NotFoundException;

@Service
public class VirementMultipleService {
		@Autowired
		VirementMultipleRepository virMulRepo;
		@Autowired
		VirementMultipleBenificiareRepository virMulBenRepo;
		@Autowired
		BeneficiareRepository benRepo;
		
		
		VirementMultiple getVirementMultiple(Long id) throws NotFoundException {
			
			VirementMultiple virmentmult = virMulRepo.findById(id).orElseThrow(() -> 
			new NotFoundException("le virment avec l'id= "+id+" est introuvable!"));
			return virmentmult;
		}
		
		
		//Methode qui va nous returner la liste des benificiare 
		List<VirementMulttipleBeneficiare> getVirementMultipleBeneficiaire(Long id) throws NotFoundException{
				
			VirementMultiple virmentmult = virMulRepo.findById(id).orElseThrow(() -> 
			new NotFoundException("le virment avec l'id= "+id+" est introuvable!"
					));
			
			List< VirementMulttipleBeneficiare> list=virmentmult.getVirementMultipleBeneficiare();
			return list;
				
			
		}
		
		//Methode qui va gerer la confirmation et la signature!!
		public void confirmer(Long id ,String password) {
			
				
				Optional<VirementMultiple> viroptionelle = virMulRepo.findById(id);
				viroptionelle.ifPresent(vir ->{//a verifier //
						if (vir.getClient().getPassword().equals(new BCryptPasswordEncoder().encode(password))) {
								vir.setStatus("Confirmé et Signé!");
								virMulRepo.save(vir);
						}
				});
			
			
		}
		
		
		public void EffectuerVirmentmultiple(VirementMultiple virmentmul) throws NotFoundException {
			VirementMulttipleBeneficiare virementmulBeneficiare  = new VirementMulttipleBeneficiare();
			Optional<Beneficiare> beneficiare;
			//id beneficiare.
			Long idLong= null;
			List<VirementMulttipleBeneficiare> list=new ArrayList<VirementMulttipleBeneficiare>();
			virMulRepo.save(virmentmul);
			 
			 for (int i=0;i<virmentmul.getNombreDeBeneficiare();i++) {
				 
				 	virementmulBeneficiare = virmentmul.getVirementMultipleBeneficiare().get(i);
				 	idLong=virementmulBeneficiare.getBeneficiare().getId();
				 	beneficiare =benRepo.findById(idLong);
				 	if(beneficiare.isPresent()) {
				 			
				 			virementmulBeneficiare.setBeneficiare(beneficiare.get());
				 	}else {
						throw new NotFoundException("le benificiaire specifie introuvable! ");
					}
				 	virementmulBeneficiare.setVirementMultiple(virmentmul);
				 	virMulBenRepo.save(virementmulBeneficiare);
				 	list.add(virementmulBeneficiare);
				 	
				 	
			}
			 virmentmul.setVirementMultipleBeneficiare(list);
			 
			 virMulRepo.save(virmentmul);
			 
			 
			
			
		}
		
		
		
		
		
	
}
