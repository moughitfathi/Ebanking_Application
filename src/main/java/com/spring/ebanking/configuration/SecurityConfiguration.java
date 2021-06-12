package com.spring.ebanking.configuration;

import java.util.Arrays;



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

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	@Autowired
	private  UserDetailServiceImpl uservice;
	
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
		final  CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
		configuration.setAllowedMethods(Arrays.asList("HEAD",
				"GET", "POST", "PUT", "DELETE"));
		configuration.setAllowedHeaders(Arrays.asList("accept",
				"accept-encoding",
				"authorization",
				"content-type",
				"dnt",
				"origin",
				"user-agent",
				"x-csrftoken",
				"x-requested-with"));
		
		
		configuration.setAllowCredentials(true);
		
		configuration.setAllowedHeaders(Arrays.asList("Authorization", "Cache-Control", "Content-Type"));
		final  UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}

	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("admin").password(passencoder().encode("0123")).roles("Admin");
		auth.inMemoryAuthentication().withUser("banquier").password(passencoder().encode("0123")).roles("Banquier");
		auth.inMemoryAuthentication().withUser("client").password(passencoder().encode("0123")).roles("Client");
		
		
	}
	
	@Override
	protected void configure(HttpSecurity http)  throws Exception{
	//at the end remove crsf().disable()
		
		//http.formLogin().loginPage("/login")
		
		http
		.cors().and().
		authorizeRequests()
		.antMatchers("/admin**/**/**/**").hasRole("Admin")
		.antMatchers("/banquier**/**/**/**").hasRole("Banquier")
		.antMatchers("/client**/**/**/**").hasRole("Client")
		.antMatchers("/").hasRole("USER")
		.and().formLogin().and().csrf().disable();
		
		
		
		
	
	
	}

	
	
}
