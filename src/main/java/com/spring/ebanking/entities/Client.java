package com.spring.ebanking.entities;


import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.CascadeType;
import javax.persistence.Column;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;


@Data @NoArgsConstructor @AllArgsConstructor 
@Entity
public class Client extends Personne {
	
		@Column(nullable = false,unique = true)
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
		@Column(nullable = false)
		private Role role;
		
		
		
		
		
			
	

	
	
}
