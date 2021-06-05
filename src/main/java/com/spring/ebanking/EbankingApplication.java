package com.spring.ebanking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.spring.ebanking.entities.Client;
import com.spring.ebanking.repositories.ClientRepository;

@SpringBootApplication
public class EbankingApplication {

	public static void main(String[] args) {
		SpringApplication.run(EbankingApplication.class, args);
	}
	
}
