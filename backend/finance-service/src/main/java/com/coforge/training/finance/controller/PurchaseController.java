package com.coforge.training.finance.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.coforge.training.finance.model.Purchase;
import com.coforge.training.finance.service.PurchaseService;

@RestController
@RequestMapping("/api/purchases")
@CrossOrigin(origins = "http://localhost:4200")
public class PurchaseController {

    @Autowired
    private PurchaseService purchaseService;

    // Add Purchase
    @PostMapping
    public ResponseEntity<Purchase> addPurchase(@RequestBody Purchase purchase) {
        Purchase savedPurchase = purchaseService.addPurchase(purchase);
        return new ResponseEntity<>(savedPurchase, HttpStatus.CREATED);
    }

    // Get All Purchases
    @GetMapping
    public ResponseEntity<List<Purchase>> getAllPurchases() {
        return new ResponseEntity<>(purchaseService.getAllPurchases(), HttpStatus.OK);
    }

    // Get Purchase By Id
    @GetMapping("/{id}")
    public ResponseEntity<Purchase> getPurchaseById(@PathVariable Long id) {
        Purchase purchase = purchaseService.getPurchaseById(id);

        if (purchase != null) {
            return new ResponseEntity<>(purchase, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Update Purchase
    @PutMapping("/{id}")
    public ResponseEntity<Purchase> updatePurchase(@PathVariable Long id,
                                                   @RequestBody Purchase purchase) {

        Purchase updatedPurchase = purchaseService.updatePurchase(id, purchase);

        if (updatedPurchase != null) {
            return new ResponseEntity<>(updatedPurchase, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Delete Purchase
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePurchase(@PathVariable Long id) {

        purchaseService.deletePurchase(id);

        return new ResponseEntity<>("Purchase Deleted Successfully",
                HttpStatus.OK);
    }
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Purchase>> getPurchasesByUser(@PathVariable Long userId) {

        return ResponseEntity.ok(
                purchaseService.getPurchasesByUser(userId));

    }

}