package com.finance.productservice.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.finance.productservice.dto.ApiResponse;
import com.finance.productservice.dto.ProductRequest;
import com.finance.productservice.dto.ProductResponse;
import com.finance.productservice.dto.PurchaseDTO;
import com.finance.productservice.dto.PurchaseRequestDTO;
import com.finance.productservice.service.ProductService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "http://localhost:4200")
@Validated
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<ProductResponse>> addProduct(
            @Valid @RequestBody ProductRequest request) {

        ProductResponse response = productService.addProduct(request);

        return new ResponseEntity<>(
                new ApiResponse<>(
                        true,
                        "Product added successfully",
                        response,
                        LocalDateTime.now()),
                HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<ProductResponse>>> getAllProducts() {

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Products fetched successfully",
                        productService.getAllProducts(),
                        LocalDateTime.now()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ProductResponse>> getProductById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Product fetched successfully",
                        productService.getProductById(id),
                        LocalDateTime.now()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ProductResponse>> updateProduct(
            @PathVariable Long id,
            @Valid @RequestBody ProductRequest request) {

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Product updated successfully",
                        productService.updateProduct(id, request),
                        LocalDateTime.now()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deleteProduct(
            @PathVariable Long id) {

        productService.deleteProduct(id);

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Product deleted successfully",
                        "Deleted",
                        LocalDateTime.now()));
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<ApiResponse<List<ProductResponse>>> getProductsByCategory(
            @PathVariable String category) {

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Products fetched successfully",
                        productService.getProductsByCategory(category),
                        LocalDateTime.now()));
    }

    @GetMapping("/search")
    public ResponseEntity<ApiResponse<List<ProductResponse>>> searchProduct(
            @RequestParam String name) {

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Search completed successfully",
                        productService.searchProductsByName(name),
                        LocalDateTime.now()));
    }
    
    @PostMapping("/purchase")
    public ResponseEntity<String> purchaseProduct(@RequestBody PurchaseRequestDTO request) {
        return ResponseEntity.ok(productService.purchaseProduct(request));
    }
    // Internal endpoint for Feign - Get All Products
    @GetMapping("/internal/products")
    public List<ProductResponse> getProductsForFeign() {
        return productService.getAllProducts();
    }

    // Internal endpoint for Feign - Get Product By Id
    @GetMapping("/internal/products/{id}")
    public ProductResponse getProductForFeign(@PathVariable Long id) {
        return productService.getProductById(id);
    }
}