package com.spring.ebanking.entities;


import java.util.List;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Data@AllArgsConstructor@NoArgsConstructor

@DiscriminatorValue("MULT")
public class VirementMultiple  extends Virement{
	
	@Column(nullable = false)
	private Integer nombreDeBeneficiare;
	@Column(nullable=false)
	private String status="enregistre";
	
    @JsonIgnore
	@OneToMany(mappedBy = "virementMultiple")
	private List<VirementMulttipleBeneficiare> virementMultipleBeneficiare;

}
