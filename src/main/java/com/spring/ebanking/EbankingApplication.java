package com.spring.ebanking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.spring.ebanking.repositories.ClientRepository;

@SpringBootApplication
@EnableAutoConfiguration

public class EbankingApplication{
	
	@Autowired
	ClientRepository clientRepository;
	
	/*
	 * @Autowired private RepositoryRestConfiguration restConfiguration;
	 */

	
	
	public static void main(String[] args) {
		SpringApplication.run(EbankingApplication.class, args);
		
		PasswordEncoder pe=new BCryptPasswordEncoder();
		String p=pe.encode("0123456");
		System.out.println(p);
	}

	/*
	 * @Override public void run(String... args) throws Exception {
	 * restConfiguration.exposeIdsFor(Client.class);
	 * 
	 * Date d1 = new Date(); Date d2 = new Date(); Role role = new Role();
	 * role.setRole("client");
	 * 
	 * clientRepository.save(new Client("yassine", "ss", "ddd", "0666669966", d1,
	 * d2, "simdo@exemple.com", "simddo123", "EE443456",role));
	 * 
	 * 
	 * }
	 */
}


     