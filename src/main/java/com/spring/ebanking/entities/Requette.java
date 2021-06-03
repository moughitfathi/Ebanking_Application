package com.spring.ebanking.entities;

import java.sql.Date;

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
}
