package com.spring.ebanking.entities;


import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
public class RDV extends Requette{
	
	private String motif;

}
