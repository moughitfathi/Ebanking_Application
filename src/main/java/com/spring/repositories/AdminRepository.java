package com.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.ebanking.entities.Admin;

public interface AdminRepository extends JpaRepository<Admin, Long> {

}
