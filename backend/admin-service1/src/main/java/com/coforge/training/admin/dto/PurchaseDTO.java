package com.coforge.training.admin.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class PurchaseDTO {

    private Long purchaseId;
    private Long userId;
    private Long productId;
    private Double totalAmount;
    private Integer emiDuration;
    private Double emiAmount;
    private LocalDate purchaseDate;
    private String status;

}