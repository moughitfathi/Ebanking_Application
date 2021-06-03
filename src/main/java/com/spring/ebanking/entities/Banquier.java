package com.spring.ebanking.entities;

import java.util.List;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data

public class Banquier extends Personne {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String email;
	private String password;
	private String cin;
	@ManyToOne
	@JoinColumn(name="ID_AGENCE")
	private Agence lieuTravaille;
	
	@OneToMany(mappedBy="banquier",fetch=FetchType.LAZY)
	private List<Requette>  listeRequettes;
	
	@OneToOne(mappedBy = "role",fetch = FetchType.LAZY)
	private Role role;
 
}
