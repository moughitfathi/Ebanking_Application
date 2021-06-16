package com.spring.ebanking.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor 
@Entity
public class Role {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id ;
	@Column(unique = true,nullable = false)
	private String role;
	
    @JsonIgnore
	@OneToMany(mappedBy = "role",fetch=FetchType.LAZY,cascade = CascadeType.ALL)
	private List<Client> clients;
	
    @JsonIgnore
	@OneToMany(mappedBy = "role",fetch=FetchType.LAZY,cascade = CascadeType.ALL)
	private List<Banquier> banquiers;
	
    @JsonIgnore
	@OneToMany(mappedBy = "role",fetch=FetchType.LAZY,cascade = CascadeType.ALL)
	private List<Admin> admins;
}
