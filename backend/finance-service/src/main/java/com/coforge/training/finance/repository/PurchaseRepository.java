package com.coforge.training.finance.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.coforge.training.finance.model.Purchase;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, Long> {

    List<Purchase> findByUserId(Long userId);
    
    

}