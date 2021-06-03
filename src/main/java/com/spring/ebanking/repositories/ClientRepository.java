package com.spring.ebanking.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.ebanking.entities.Client;

public interface ClientRepository extends JpaRepository<Client, Long>{

}
