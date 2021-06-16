package com.spring.ebanking.entities;


import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
    @JsonIgnore
	@ManyToOne
	@JoinColumn(name="ID_RDV")
	//@Column(unique = true,nullable  = false)
	private Client client ;
	

    @JsonIgnore
	@ManyToOne
	@JoinColumn(name = "ID_BANQUIER")
	private Banquier banquier;

}
