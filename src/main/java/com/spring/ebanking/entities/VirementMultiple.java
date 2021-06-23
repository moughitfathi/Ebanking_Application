package com.spring.ebanking.entities;


import java.util.List;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
@Entity
@Data@AllArgsConstructor@NoArgsConstructor
@EqualsAndHashCode(exclude = "virementMultipleBeneficiare")
@DiscriminatorValue("MULT")
@ToString(exclude = {"virementMultipleBeneficiare"} )

public class VirementMultiple  extends Virement{
	
	@Column(nullable = false)
	private Integer nombreDeBeneficiare;
	@Column(nullable=false)
	private String status="enregistre";
	
    @JsonIgnore
	@OneToMany(mappedBy = "virementMultiple")

	private List<VirementMulttipleBeneficiare> virementMultipleBeneficiare;

}
	