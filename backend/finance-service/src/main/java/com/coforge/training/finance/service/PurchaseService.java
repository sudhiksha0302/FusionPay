package com.coforge.training.finance.service;

import java.util.List;

import com.coforge.training.finance.model.Purchase;

public interface PurchaseService {

    Purchase addPurchase(Purchase purchase);

    List<Purchase> getAllPurchases();

    Purchase getPurchaseById(Long purchaseId);

    Purchase updatePurchase(Long purchaseId, Purchase purchase);

    void deletePurchase(Long purchaseId);
    
    List<Purchase> getPurchasesByUser(Long userId);

}