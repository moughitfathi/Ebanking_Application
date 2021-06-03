package com.spring.ebanking.entities;


import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data@NoArgsConstructor@AllArgsConstructor
public class Beneficiare {
	
	@Id@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nom;
	private String prenom;
	private int numeroDecompte ;
	
	@OneToMany(mappedBy="beneficiare",fetch = FetchType.LAZY)
	private List<VirementMulttipleBeneficiare> virementMultipleBeneficiare;
	
	
	
}
