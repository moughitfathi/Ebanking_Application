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

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"listeComptes","listeBanquiers", "listeClients"} )
public class Agence {

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@Column(unique=true,nullable = false)
	private String nom;
	@Column(nullable = false)
	private String ville;
	@Column(nullable = false)
	private String adresse;
	@Column(unique = true,nullable=false)
	private String email;
	@Column(unique = true,nullable=false)
	private String tel;
	

	

	@JsonIgnore
	@OneToMany(mappedBy="agence",fetch=FetchType.LAZY)
	
	private List<Banquier>  listeBanquiers;
	
    @JsonIgnore
	@OneToMany(mappedBy = "agence",fetch = FetchType.LAZY)
	private List<Compte> listeComptes;
	
    @JsonIgnore
	@OneToMany(mappedBy = "agence",fetch = FetchType.LAZY)
	private List<Client> listeClients;
    
 	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne
	@JoinColumn(name="ID_ADMIN")
	private Admin admin;

}
