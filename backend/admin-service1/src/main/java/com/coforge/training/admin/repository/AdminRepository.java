package com.coforge.training.admin.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.coforge.training.admin.model.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long>{

	Optional<Admin> findByUsername(String username);

	Optional<Admin> findByEmail(String email);

}