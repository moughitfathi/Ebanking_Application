package com.spring.ebanking.entities;


import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.CascadeType;
import javax.persistence.Column;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;


@Data @NoArgsConstructor @AllArgsConstructor 
@Entity
public class Client extends Personne {
	
	
	
	/*
	 * public Client( String prenom, String nom, String adresse, String tel, Date
	 * dateNaissance, Date dateInscription,String email,String password,String
	 * cin,Role rolee) { super( prenom, nom, adresse, tel, dateNaissance,
	 * dateInscription); this.email=email; this.password=password; this.cin=cin;
	 * this.role=rolee;
	 * 
	 * }
	 * 
	 * @Column(nullable = false,unique = true) private String email ; private String
	 * password ;
	 * 
	 * @Column(nullable = false,unique = true) private String cin ;
	 */		
		
		

	     @JsonIgnore
		@OneToMany(mappedBy = "client",fetch=FetchType.LAZY)
		private List<Compte> comptes;

	     @JsonIgnore
		@OneToMany(mappedBy = "client",fetch=FetchType.LAZY)
		private List<Beneficiare> beneficiaires;
		
	     @JsonIgnore
		@OneToMany(mappedBy = "client",fetch=FetchType.LAZY)
		private List<RDV> rendezVous;
		
	     @JsonIgnore
		@OneToMany(mappedBy = "client",fetch=FetchType.LAZY)
		private List<Virement> virements;
		
		//@Column(nullable = false)
	 	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
		@ManyToOne(fetch=FetchType.LAZY)
		private Banquier banquier;
	 	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
		@ManyToOne(fetch=FetchType.LAZY)
		private Agence agence ;
		
		
		
		
		
			
	

	
	
}
