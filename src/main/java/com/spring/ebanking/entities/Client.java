package com.spring.ebanking.entities;

<<<<<<< HEAD
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
=======




import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
>>>>>>> refs/remotes/master/master

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data @NoArgsConstructor @AllArgsConstructor 
@Entity
public class Client extends Personne {
	
		private	String email ;
		private	String password ;
		private String cin ;
		
		
		@OneToMany(mappedBy = "client",fetch=FetchType.LAZY,cascade = CascadeType.ALL)
		private List<Compte> comptes;
		
		@OneToMany(mappedBy = "client",fetch=FetchType.LAZY,cascade = CascadeType.ALL)
		private List<Beneficiare> beneficiaires;
		
		
		@OneToMany(mappedBy = "client",fetch=FetchType.LAZY,cascade = CascadeType.ALL)
		private List<RDV> rendezVous;
		
		
		@OneToMany(mappedBy = "client",fetch=FetchType.LAZY,cascade = CascadeType.ALL)
		private List<Virement> virements;
		
		@OneToOne(mappedBy = "client",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
		private Role role;
		
		
		
		
		
			
	

	
	
}