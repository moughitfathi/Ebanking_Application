package com.spring.ebanking.entities;

import java.math.BigDecimal;
import java.util.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor@NoArgsConstructor
public class Virement {
	
	private Date dateCreation;
	private Date dateExecution;
	private BigDecimal mentant;
	private String motif;
}
