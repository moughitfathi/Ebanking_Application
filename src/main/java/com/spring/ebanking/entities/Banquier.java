package com.spring.ebanking.entities;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data

public class Banquier extends Personne {

	/*
	 * @Column(unique = true,nullable = false) private String email;
	 * 
	 * @Column(nullable = false) private String password;
	 * 
	 * @Column(unique = true,nullable = false) private String cin;
	 */
	@ManyToOne
	@JoinColumn(name="ID_AGENCE")
	private Agence agence;
	
	@OneToMany(mappedBy="banquier",fetch=FetchType.LAZY)
	private Collection<RDV>  listeRendez_vous;
	
	
   @OneToMany(mappedBy="banquier",fetch = FetchType.LAZY)
  private Collection<Client> listeClients;
   
   @ManyToOne
   @JoinColumn(name="ID_ADMIN")
   private Admin admin;
   
   @OneToMany(mappedBy="banquier",fetch = FetchType.LAZY)
   List<CreneauDispo> listeCreneauDispos;
}
