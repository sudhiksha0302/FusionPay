package com.coforge.training.admin.dto;

import lombok.Data;

@Data
public class ProductDTO {

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