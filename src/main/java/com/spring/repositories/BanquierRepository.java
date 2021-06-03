package com.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.ebanking.entities.Banquier;

public interface BanquierRepository extends JpaRepository<Banquier, Long> {

}
