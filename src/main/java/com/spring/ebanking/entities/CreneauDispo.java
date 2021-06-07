package com.spring.ebanking.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data  @AllArgsConstructor @NoArgsConstructor @Entity
public class CreneauDispo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;
	@Column(nullable = false)
	private Date dateDebut;
	@Column(nullable = false)
	private Date dateFin;
	private Boolean Status=false;  
	
	@ManyToOne
	@JoinColumn(name="ID_BANQUIER")
	private Banquier banquier;
	
	
	

}
