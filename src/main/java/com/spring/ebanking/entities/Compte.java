package com.spring.ebanking.entities;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor 
@Entity
public class Compte {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id ;
	private int numero ;
	private BigDecimal solde ;
	
	@ManyToOne(fetch=FetchType.LAZY,cascade = CascadeType.ALL)
	private Client client;
	
	@OneToMany(mappedBy = "compte",fetch=FetchType.LAZY,cascade = CascadeType.ALL)
	private List<Virement> virements;
	
	@ManyToOne(fetch=FetchType.LAZY,cascade = CascadeType.ALL)
	private Agence agence;
	
	
	
	
	

	
	
}
