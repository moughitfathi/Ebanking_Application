package com.spring.ebanking.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Data@AllArgsConstructor
@NoArgsConstructor
public class Admin extends Personne {

	/*
	 * @Column(unique = true,nullable = false) private String email;
	 * 
	 * @Column(nullable = false) private String password;
	 * 
	 * @Column(nullable= false,unique = true) private String cin;
	 */

    @JsonIgnore
	@OneToMany(mappedBy="admin",fetch =FetchType.LAZY)
    private List<Agence> agences;

    @JsonIgnore
    @OneToMany(mappedBy="admin",fetch =FetchType.LAZY)
	List<Banquier> listeBanquiers;

	
	
}
