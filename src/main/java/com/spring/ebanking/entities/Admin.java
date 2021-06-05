package com.spring.ebanking.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Data@AllArgsConstructor
@NoArgsConstructor
public class Admin extends Personne {

	@Column(unique = true,nullable = false)
	private String email;
	@Column(nullable = false)
	private String password;
	
	@Column(nullable= false,unique = true)
	private String cin;
	
	@OneToOne(mappedBy ="role",fetch = FetchType.LAZY)
	@Column(nullable = false)
    private Role role;
	
	@OneToMany(mappedBy="admin",fetch = FetchType.LAZY)
	@Column(nullable = false)
	private Admin admin;
	
	
}
