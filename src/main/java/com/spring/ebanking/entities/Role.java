package com.spring.ebanking.entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor 
@Entity
public class Role {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id ;
	private String role;
	
	@OneToOne(mappedBy = "role",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	private Client client;
	
	@OneToOne(mappedBy = "role",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	private Banquier banquier;

}
