package com.spring.ebanking.configuration;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.spring.ebanking.entities.Personne;




public class UserDetailmpl implements UserDetails{
	
	
	private static final long serialVersionUID = 1L;
	Personne personne;
	@Autowired
	public UserDetailmpl(Personne p){
		personne=p;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		GrantedAuthority authority= new SimpleGrantedAuthority("ROLE_"+this.personne.getRole().getRole());
		authorities.add(authority);
		return authorities;
	}

	@Override
	public String getPassword() {
		
		return personne.getPassword();
		
	}

	@Override
	public String getUsername() {
		return personne.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		
		return false;
	}

	@Override
	public boolean isAccountNonLocked() {
		
		return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		
		return false;
	}

	@Override
	public boolean isEnabled() {
		
		return personne.isActive();
	}
		
	
}
