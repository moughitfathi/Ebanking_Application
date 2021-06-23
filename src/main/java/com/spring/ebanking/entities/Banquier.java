package com.spring.ebanking.entities;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString(exclude = {"listeRendez_vous","listeCreneauDispos", "listeClients"} )

public class Banquier extends Personne {

	/*
	 * @Column(unique = true,nullable = false) private String email;
	 * 
	 * @Column(nullable = false) private String password;
	 * 
	 * @Column(unique = true,nullable = false) private String cin;
	 */
 	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@ManyToOne
	@JoinColumn(name="ID_AGENCE")
	private Agence agence;
	
    
    @JsonIgnore
	@OneToMany(mappedBy="banquier",fetch=FetchType.LAZY)
	private Collection<RDV>  listeRendez_vous;
	
    @JsonIgnore
   @OneToMany(mappedBy="banquier",fetch = FetchType.LAZY)
  private Collection<Client> listeClients;
   
 	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne
   @JoinColumn(name="ID_ADMIN")
   private Admin admin;
   
    @JsonIgnore
   @OneToMany(mappedBy="banquier",fetch = FetchType.LAZY)
   List<CreneauDispo> listeCreneauDispos;
}
