package com.coforge.training.userservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coforge.training.userservice.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByEmail(String email);

    boolean existsByUsername(String username);

    boolean existsByPhoneNumber(String phoneNumber);

    Optional<User> findByUsername(String username);
    
    Optional<User> findByPhoneNumber(String phoneNumber);

}