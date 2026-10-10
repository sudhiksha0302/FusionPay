package com.coforge.training.finance.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coforge.training.finance.model.Purchase;
import com.coforge.training.finance.repository.PurchaseRepository;

@Service
public class PurchaseServiceImpl implements PurchaseService {

    @Autowired
    private PurchaseRepository purchaseRepository;

    @Override
    public Purchase addPurchase(Purchase purchase) {
        return purchaseRepository.save(purchase);
    }

    @Override
    public List<Purchase> getAllPurchases() {
        return purchaseRepository.findAll();
    }

    @Override
    public Purchase getPurchaseById(Long purchaseId) {
        return purchaseRepository.findById(purchaseId).orElse(null);
    }

    @Override
    public Purchase updatePurchase(Long purchaseId, Purchase purchase) {

        Purchase existingPurchase = purchaseRepository.findById(purchaseId).orElse(null);

        if (existingPurchase != null) {
            existingPurchase.setUserId(purchase.getUserId());
            existingPurchase.setProductId(purchase.getProductId());
            existingPurchase.setTotalAmount(purchase.getTotalAmount());
            existingPurchase.setEmiDuration(purchase.getEmiDuration());
            existingPurchase.setEmiAmount(purchase.getEmiAmount());
            existingPurchase.setPurchaseDate(purchase.getPurchaseDate());
            existingPurchase.setStatus(purchase.getStatus());

            return purchaseRepository.save(existingPurchase);
        }

        return null;
    }

    @Override
    public void deletePurchase(Long purchaseId) {
        purchaseRepository.deleteById(purchaseId);
    }
    
    @Override
    public List<Purchase> getPurchasesByUser(Long userId) {

        return purchaseRepository.findByUserId(userId);

    }

}