package com.finance.productservice.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductResponse {

    private Long productId;
    private String productName;
    private String description;
    private String brand;
    private String category;
    private Double price;
    private Integer stock;
    private String imageUrl;
    private Boolean active;
}