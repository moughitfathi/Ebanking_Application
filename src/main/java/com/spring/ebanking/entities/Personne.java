package com.spring.ebanking.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;


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
	
	 
	 
	
	
}
