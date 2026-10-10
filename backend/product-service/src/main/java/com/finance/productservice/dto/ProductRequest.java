package com.finance.productservice.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ProductRequest {

    @NotBlank(message = "Product name is required")
    private String productName;

    private String description;

    @NotBlank(message = "Brand is required")
    private String brand;

    @NotBlank(message = "Category is required")
    private String category;

    @NotNull(message = "Price is required")
    @Min(value = 1, message = "Price must be greater than 0")
    private Double price;

    @NotNull(message = "Stock is required")
    @Min(value = 0, message = "Stock cannot be negative")
    private Integer stock;

    private String imageUrl;

    private Boolean active;
}