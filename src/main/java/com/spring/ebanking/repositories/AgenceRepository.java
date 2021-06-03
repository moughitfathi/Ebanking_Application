package com.spring.ebanking.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.ebanking.entities.Agence;

public interface AgenceRepository extends JpaRepository<Agence,Long> {

}
