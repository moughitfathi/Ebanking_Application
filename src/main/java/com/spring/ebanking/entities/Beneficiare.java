package com.spring.ebanking.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data@NoArgsConstructor@AllArgsConstructor
public class Beneficiare {

	private String nom;
	private String prenom;
	private int numeroDecompte;
}
