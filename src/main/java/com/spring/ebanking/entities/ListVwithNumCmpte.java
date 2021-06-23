package com.spring.ebanking.entities;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor 
public class ListVwithNumCmpte {

	private int numCompte;
	private BigDecimal solde;
	
}
