package com.spring.ebanking.entities;


import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
		
		

		@OneToMany(mappedBy = "client",fetch=FetchType.LAZY,cascade = CascadeType.ALL)
		private List<Compte> comptes;
		
		@OneToMany(mappedBy = "client",fetch=FetchType.LAZY,cascade = CascadeType.ALL)
		private List<Beneficiare> beneficiaires;
		
		
		@OneToMany(mappedBy = "client",fetch=FetchType.LAZY,cascade = CascadeType.ALL)
		private List<RDV> rendezVous;
		
		
		@OneToMany(mappedBy = "client",fetch=FetchType.LAZY,cascade = CascadeType.ALL)
		private List<Virement> virements;
		
		//@Column(nullable = false)
		
		
		@ManyToOne(fetch=FetchType.LAZY,cascade = CascadeType.ALL)
		@JsonIgnore
		private Banquier banquier;
		
		@ManyToOne(fetch=FetchType.LAZY,cascade = CascadeType.ALL)
		@JsonIgnore
		private Agence agence ;
		
		
		
		
		
			
	

	
	
}
