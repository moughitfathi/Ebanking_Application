package com.spring.ebanking.entities;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor 
@Entity
public class Compte {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id ;
	@Column(unique = true,nullable = false)
	private int numero ;
	@Column(nullable = false)
	private BigDecimal solde ;
	
	@ManyToOne(fetch=FetchType.LAZY,cascade = CascadeType.ALL)
	@JsonIgnore
	private Client client;
	
	@OneToMany(mappedBy = "compte",fetch=FetchType.LAZY,cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Virement> virements;
	
	@ManyToOne(fetch=FetchType.LAZY,cascade = CascadeType.ALL)
	@JsonIgnore
	private Agence agence;
	
	
	
	
	

	
	
}
