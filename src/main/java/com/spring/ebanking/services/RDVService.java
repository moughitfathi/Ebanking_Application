package com.spring.ebanking.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.ebanking.entities.RDV;
import com.spring.ebanking.repositories.RDVRepository;

@Service
public class RDVService {
	
	@Autowired
	RDVRepository RDVrepo;
	
	
	
	
	//recuperer la liste des rendez-vous
	public List<RDV> getRDV(Long id)  throws Exception
	{
				
				List<RDV> listeRendez_vous= new ArrayList<RDV>();	
				if(id!=null)
					listeRendez_vous.add(RDVrepo.findById(id).orElseThrow(() -> new Exception("Aucun Rendez-vous avec l'id "+id+" trouvé")));
				
				else
					listeRendez_vous=RDVrepo.findAll();
				
				if(listeRendez_vous.isEmpty())  throw new Exception("Aucun rendez-vous trouvé");
				return listeRendez_vous;
		
	}
	
	
	
	
	
	

}
