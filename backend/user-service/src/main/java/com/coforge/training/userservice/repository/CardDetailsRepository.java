package com.coforge.training.userservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coforge.training.userservice.entity.CardDetails;

public interface CardDetailsRepository extends JpaRepository<CardDetails, Long> {

    Optional<CardDetails> findByUserUserId(Long userId);

    boolean existsByCardNumber(String cardNumber);

}