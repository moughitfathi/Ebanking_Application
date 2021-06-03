package com.spring.ebanking.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Banquier {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private String id;
	private String email;
	private String password;
	private String cin;
	@ManyToOne
	@JoinColumn(name="ID_AGENCE")
	private Agence lieuTravaille;
	
	@OneToMany(mappedBy="banquier",fetch=FetchType.LAZY)
	private List<Requette>  listeRequettes;

}
