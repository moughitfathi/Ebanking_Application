package com.spring.ebanking.configuration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.spring.ebanking.entities.Admin;
import com.spring.ebanking.entities.Role;
import com.spring.ebanking.repositories.RoleRepository;
import com.spring.ebanking.services.AdminService;

import javassist.NotFoundException;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	@Autowired
	private UserDetailServiceImpl uservice;
	@Autowired
	private AdminService adminService;
	@Autowired
	RoleRepository roleRepo;

	@Bean
	PasswordEncoder passencoder() {
		return new BCryptPasswordEncoder();

	}
	
	@Bean
	public DaoAuthenticationProvider getProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(uservice);
		provider.setPasswordEncoder(passencoder());
		return provider;

	}

	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		final CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
		configuration.setAllowedMethods(Arrays.asList("HEAD", "GET", "POST", "PUT", "DELETE"));
		configuration.setAllowedHeaders(Arrays.asList("accept", "accept-encoding", "authorization", "content-type",
				"dnt", "origin", "user-agent", "x-csrftoken", "x-requested-with"));

		configuration.setAllowCredentials(true);

		configuration.setAllowedHeaders(Arrays.asList("Authorization", "Cache-Control", "Content-Type"));
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}

	@Autowired
	private DataSource dataSource;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("admin").password(passencoder().encode("0123")).roles("admin");
		auth.inMemoryAuthentication().withUser("banquier").password(passencoder().encode("0123")).roles("banquier");
		auth.inMemoryAuthentication().withUser("client").password(passencoder().encode("0123")).roles("client");
		
		
		auth.jdbcAuthentication()
		.dataSource(dataSource)
		.usersByUsernameQuery("select username as principal,password as credentials,active from personne where username=?")
		.authoritiesByUsernameQuery("select username as principal , role as role from personne p , role r where username = ? and p.role_id =r.id")
		.rolePrefix("ROLE_").passwordEncoder(passencoder());
		
	}

@Override
	protected void configure(HttpSecurity http)  throws Exception{
	//at the end remove crsf().disable()
		
		http
		.cors().and()
		.authorizeRequests()
		.antMatchers("/admin**/**/**").hasRole("admin")
		.antMatchers("/banquier**/**/**/**").hasRole("banquier")
		.antMatchers("/client**/**/**/**").hasRole("client")
		.antMatchers("/").hasRole("USER")
		.and()
		.httpBasic()
		.and()
		.csrf().disable();
		super.configure(http);
		//.formLogin().and().csrf().disable();
		
		
		
	
	
	}

	
	
}
