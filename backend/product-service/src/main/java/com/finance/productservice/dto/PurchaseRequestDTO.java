package com.finance.productservice.dto;

import lombok.Data;

@Data
public class PurchaseRequestDTO {

    private Long userId;
    private Long productId;
    private Integer emiDuration;

}