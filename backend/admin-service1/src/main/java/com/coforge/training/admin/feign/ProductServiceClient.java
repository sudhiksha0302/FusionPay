package com.coforge.training.admin.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.coforge.training.admin.dto.ProductDTO;

@FeignClient(name = "PRODUCT-SERVICE")
public interface ProductServiceClient {

    @GetMapping("/api/products/internal/products")
    List<ProductDTO> getAllProducts();

    @GetMapping("/api/products/internal/products/{id}")
    ProductDTO getProductById(@PathVariable Long id);

}