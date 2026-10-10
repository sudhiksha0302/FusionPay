package com.coforge.training.userservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coforge.training.userservice.entity.BankDetails;

public interface BankDetailsRepository extends JpaRepository<BankDetails, Long>{

    Optional<BankDetails> findByUserUserId(Long userId);

}