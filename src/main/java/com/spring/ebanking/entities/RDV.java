package com.spring.ebanking.entities;


import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@DiscriminatorValue("RDV")
public class RDV extends Requette{
	
	private String motif;
	@ManyToOne
	@JoinColumn(name="ID_RDV")
	private Client client ;

}