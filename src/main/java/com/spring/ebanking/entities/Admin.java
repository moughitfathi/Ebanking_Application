package com.spring.ebanking.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Data@AllArgsConstructor
@NoArgsConstructor
public class Admin extends Personne {
	private Long id;
	@Column(unique = true,nullable = false)
	private String email;
	@Column(nullable = false)
	private String password;
	
	@Column(nullable= false,unique = true)
	private String cin;
	
	@OneToMany(mappedBy ="role",fetch = FetchType.LAZY)
	@Column(nullable = false)
    private Role role;
	
	
}