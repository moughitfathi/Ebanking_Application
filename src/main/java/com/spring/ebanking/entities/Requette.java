package com.spring.ebanking.entities;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="Type",length = 3)
public abstract class  Requette {

	private Long id;
	@Column(nullable  = false)
	private Date dateRequette;
	private Boolean etat;
	@ManyToOne
	@JoinColumn(name = "ID_BANQUIER")
	private Banquier banquier;
}
