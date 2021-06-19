package com.spring.ebanking.entities;


import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
public class RDV extends Requette{
	
	private String motif;
    
	@ManyToOne
	@JoinColumn(name="ID_CLIENT")
	//@Column(unique = true,nullable  = false)
	private Client client ;
	

	@ManyToOne
	@JoinColumn(name = "ID_BANQUIER")
	private Banquier banquier;

}
