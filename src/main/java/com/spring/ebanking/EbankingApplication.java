package com.spring.ebanking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration
public class EbankingApplication  {
	
	
	public static void main(String[] args) {
		SpringApplication.run(EbankingApplication.class, args);
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
