package com.finance.productservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.finance.productservice.entity.Product;


public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByCategory(String category);

    List<Product> findByProductNameContainingIgnoreCase(String productName);

    List<Product> findByBrand(String brand);

    List<Product> findByActiveTrue();

}