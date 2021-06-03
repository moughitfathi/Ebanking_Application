package com.spring.ebanking.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Agence {

	private Long id;
	private String nom;
	private String ville;
	private String adresse;
	private String email;
	private String tel;

}
