package com.spring.ebanking.entities;

import java.math.BigDecimal;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data@NoArgsConstructor@AllArgsConstructor
public class VirementMulttipleBeneficiare {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id ;
	
	private BigDecimal montant;
	
	@ManyToOne@JoinColumn(name = "id_virment_mult")
	private VirementMultiple virementMultiple;
    @ManyToOne
	@JoinColumn(name="id_benificiaire")
	private Beneficiare beneficiare;
}
