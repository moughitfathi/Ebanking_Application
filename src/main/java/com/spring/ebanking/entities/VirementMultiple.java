package com.spring.ebanking.entities;


import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Data@AllArgsConstructor@NoArgsConstructor

@DiscriminatorValue("MULT")
public class VirementMultiple  extends Virement{
	private Integer nombreDeBeneficiare;
	
	@OneToMany(mappedBy = "virementMultiple")
	private List<VirementMulttipleBeneficiare> virementMultipleBeneficiare;

}
