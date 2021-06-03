package com.spring.ebanking.entities;

import java.math.BigDecimal;
import java.util.*;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GeneratorType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor@NoArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "Type",length=4)
public class Virement {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private Date dateCreation;
	@Column(nullable = false)
	private Date dateExecution;
	@Column(nullable = false)
	private BigDecimal mentant;
	private String motif;
	
	@ManyToOne@JoinColumn(name = "id_compte")
	private Compte compte;
	@ManyToOne@JoinColumn(name = "id_client")
	private Client client;

}
