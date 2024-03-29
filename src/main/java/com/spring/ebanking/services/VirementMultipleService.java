package com.spring.ebanking.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

import com.spring.ebanking.entities.Beneficiare;
import com.spring.ebanking.entities.Compte;
import com.spring.ebanking.entities.VirementMultiple;
import com.spring.ebanking.entities.VirementMulttipleBeneficiare;
import com.spring.ebanking.repositories.BeneficiareRepository;
import com.spring.ebanking.repositories.ClientRepository;
import com.spring.ebanking.repositories.CompteRepository;
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
		@Autowired
		ClientRepository clieRepository ;
		@Autowired
		CompteRepository compteRepository;
		
		
		public VirementMultiple getVirementMultiple(Long id) throws NotFoundException {
			
			VirementMultiple virmentmult = virMulRepo.findById(id).orElseThrow(() -> 
			new NotFoundException("le virment avec l'id= "+id+" est introuvable!"));
			return virmentmult;
		}
		
		
		//Methode qui va nous returner la liste des benificiare 
		public List<VirementMulttipleBeneficiare> getVirementMultipleBeneficiaire(Long id) throws NotFoundException{
				
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
							float totale =  vir.getMentant().floatValue();
							float solde = vir.getCompte().getSolde().floatValue();
							Compte debiteur = vir.getCompte();
						if(solde >=totale) {
							List<VirementMulttipleBeneficiare> benfs = vir.getVirementMultipleBeneficiare();
							for(VirementMulttipleBeneficiare vb:benfs) {
								
							Optional<Compte> receiver = compteRepository.findByNumero(vb.getBeneficiare().getNumeroDecompte());
							receiver.ifPresent(rec ->{
									rec.setSolde(rec.getSolde().add(vb.getMontant()));
									debiteur.setSolde(debiteur.getSolde().add(vb.getMontant().negate()));
									compteRepository.save(rec);
									compteRepository.save(debiteur);
									
							});
								
							}
							vir.setStatus("Confirmé et Signé!");
							virMulRepo.save(vir);
							
						}
							
							
							

				}
						
															
				}
			);
		
		
}
		
		
		public void EffectuerVirmentmultiple(VirementMultiple virmentmul) throws NotFoundException {
			VirementMulttipleBeneficiare virementmulBeneficiare  = new VirementMulttipleBeneficiare();
			Optional<Beneficiare> beneficiare;
			//id beneficiare.
			Long idLong= null;
			List<VirementMulttipleBeneficiare> list=new ArrayList<VirementMulttipleBeneficiare>();
			virMulRepo.save(virmentmul);
System.out.println(virmentmul.getNombreDeBeneficiare());			 
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
				 	list.add(virementmulBeneficiare);
				 	
			}
			virMulBenRepo.saveAll(virmentmul.getVirementMultipleBeneficiare());
			 virmentmul.setVirementMultipleBeneficiare(list);
			 
			 virMulRepo.save(virmentmul);
			 
			 
			
			
		}
		
		
		
		
		
	
}
