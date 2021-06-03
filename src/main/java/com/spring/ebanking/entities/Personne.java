package com.spring.ebanking.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data @NoArgsConstructor @AllArgsConstructor 
@Entity
public class Personne {

	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id ;
	 private String prenom ;
	 private String nom ;
	 private String adresse ;
	 private String tel ;
	 private Date dateNaissance ;
	 private Date dateInscription;
	
	
	
}
