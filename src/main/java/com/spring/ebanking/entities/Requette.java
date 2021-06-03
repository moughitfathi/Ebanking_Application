package com.spring.ebanking.entities;

import java.sql.Date;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Requette {

	private Long id;
	private Date dateRequette;
	private Boolean etat;
	@ManyToOne
	@JoinColumn(name = "ID_BANQUIER")
	private Banquier banquier;
}
