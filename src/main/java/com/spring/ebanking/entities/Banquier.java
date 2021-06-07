package com.spring.ebanking.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
	@Column(unique = true,nullable = false)
	private String email;
	@Column(nullable = false)
	private String password;
	@Column(unique = true,nullable = false)
	private String cin;
	@ManyToOne
	@JoinColumn(name="ID_AGENCE")
	private Agence agence;
	
	@OneToMany(mappedBy="banquier",fetch=FetchType.LAZY)
	private List<RDV>  listeRendez_vous;
	
	@OneToOne(mappedBy = "role",fetch = FetchType.LAZY)
	@Column(nullable = false)
	private Role role;
	
   @OneToMany(mappedBy="banquier",fetch = FetchType.LAZY)
   List<Client> listeClients;
   
   @ManyToOne
   @JoinColumn(name="ID_ADMIN")
   private Admin admin;
   
   @OneToMany(mappedBy="banquier",fetch = FetchType.LAZY)
   List<CreneauDispo> listeCreneauDispos;
}
