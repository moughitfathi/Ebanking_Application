package com.spring.ebanking.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Agence {

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String nom;
	private String ville;
	private String adresse;
	private String email;
	private String tel;
	@OneToMany(mappedBy="lieuTravaille",fetch=FetchType.LAZY)
	private List<Banquier>  listeBanquiers;

}
