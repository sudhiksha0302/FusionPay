package com.coforge.training.finance.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "purchase")
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long purchaseId;

    private Long userId;

    private Long productId;

    private Double totalAmount;

    private Integer emiDuration;

    private Double emiAmount;

    private LocalDate purchaseDate;

    private String status;

    public Purchase() {
    }

    public Purchase(Long purchaseId, Long userId, Long productId, Double totalAmount,
                    Integer emiDuration, Double emiAmount,
                    LocalDate purchaseDate, String status) {
        this.purchaseId = purchaseId;
        this.userId = userId;
        this.productId = productId;
        this.totalAmount = totalAmount;
        this.emiDuration = emiDuration;
        this.emiAmount = emiAmount;
        this.purchaseDate = purchaseDate;
        this.status = status;
    }

    public Long getPurchaseId() {
        return purchaseId;
    }

    public void setPurchaseId(Long purchaseId) {
        this.purchaseId = purchaseId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Integer getEmiDuration() {
        return emiDuration;
    }

    public void setEmiDuration(Integer emiDuration) {
        this.emiDuration = emiDuration;
    }

    public Double getEmiAmount() {
        return emiAmount;
    }

    public void setEmiAmount(Double emiAmount) {
        this.emiAmount = emiAmount;
    }

    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(LocalDate purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}