package com.spring.ebanking.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.spring.ebanking.entities.VirementMultiple;
import com.spring.ebanking.entities.VirementMulttipleBeneficiare;
import com.spring.ebanking.services.VirementMultipleService;

@RestController
@CrossOrigin(origins = "http://localhost:4200/")

public class VirementController {

	@Autowired
	VirementMultipleService virementMultipleService;
	
	
	@GetMapping("/client/virementmultiple")
	@ResponseStatus(HttpStatus.OK)
	public VirementMultiple getVirementMultiple(@RequestParam(value = "id") Long id) throws Exception{
		
	return 	virementMultipleService.getVirementMultiple(id);
		
		
	}
	
	@PostMapping("/client/virementmultiple")
	@ResponseStatus(HttpStatus.CREATED)
	public void EffectuerVirement(@RequestBody VirementMultiple virementMultiple)  throws Exception{
		virementMultipleService.EffectuerVirmentmultiple(virementMultiple);
	}
	@GetMapping("/client/virementmultiple/{id}/virementmultiplebeneficiaires")
	@ResponseStatus(HttpStatus.OK)
	public List<VirementMulttipleBeneficiare> getVirementMulttipleBeneficiares(@PathVariable(value="id") Long id) throws Exception{
		return virementMultipleService.getVirementMultipleBeneficiaire(id);
	}
	@PutMapping("/client/virementmultiple/{id}/confirme")
	@ResponseStatus(HttpStatus.OK)
	public void confirme(@PathVariable(value="id") Long id,@RequestBody String motpasse) {
		virementMultipleService.confirmer(id, motpasse);
	}

}
