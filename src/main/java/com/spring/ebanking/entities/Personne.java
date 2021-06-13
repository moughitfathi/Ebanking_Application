package com.spring.ebanking.entities;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data  @NoArgsConstructor@AllArgsConstructor
@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public  abstract class  Personne {

	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id ;
	@Column(nullable = false)
	 private String prenom ;
	@Column(nullable = false)
	 private String nom ;
	@Column(nullable = false)
	 private String adresse ;
	@Column(nullable = false)
	 private String tel ;
	@Column(nullable = false)
	 private Date dateNaissance ;
	@Column(nullable = false)
	 private Date dateInscription;
	@Column(unique = true,nullable = false)
	private String email;
	@Column(nullable = false)
	private String password;
	@Column(unique = true,nullable = false)
	private String cin;
	@Column(nullable = false,unique = true)
	 private String username ;
	@ManyToOne(fetch=FetchType.LAZY,cascade = CascadeType.ALL)
	private Role role;
	
	private boolean active=true;
	
	
	
}
