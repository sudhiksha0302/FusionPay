package com.finance.productservice.service.impl;

import java.util.List;

import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.finance.productservice.dto.ProductRequest;
import com.finance.productservice.dto.ProductResponse;
import com.finance.productservice.entity.Product;
import com.finance.productservice.exception.ProductNotFoundException;
import com.finance.productservice.repository.ProductRepository;
import com.finance.productservice.service.ProductService;

import java.time.LocalDate;

import com.finance.productservice.dto.PurchaseDTO;
import com.finance.productservice.dto.PurchaseRequestDTO;
import com.finance.productservice.dto.UserDTO;
import com.finance.productservice.feign.FinanceServiceClient;
import com.finance.productservice.feign.UserServiceClient;

@Service
public class ProductServiceImpl implements ProductService {

    private static final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

    private final ProductRepository productRepository;
    private final UserServiceClient userServiceClient;
    private final FinanceServiceClient financeServiceClient;

    public ProductServiceImpl(ProductRepository productRepository,
                              UserServiceClient userServiceClient,
                              FinanceServiceClient financeServiceClient) {
        this.productRepository = productRepository;
        this.userServiceClient = userServiceClient;
        this.financeServiceClient = financeServiceClient;
    }

    @Override
    public ProductResponse addProduct(ProductRequest request) {

        logger.info("Adding new product: {}", request.getProductName());

        Product product = Product.builder()
                .productName(request.getProductName())
                .description(request.getDescription())
                .brand(request.getBrand())
                .category(request.getCategory())
                .price(request.getPrice())
                .stock(request.getStock())
                .imageUrl(request.getImageUrl())
                .active(request.getActive())
                .build();

        Product savedProduct = productRepository.save(product);

        logger.info("Product added successfully with ID: {}", savedProduct.getProductId());

        return mapToResponse(savedProduct);
    }

    @Override
    public List<ProductResponse> getAllProducts() {

        logger.info("Fetching all products");

        return productRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public ProductResponse getProductById(Long productId) {

        logger.info("Fetching product with ID: {}", productId);

        Product product = productRepository.findById(productId)
                .orElseThrow(() ->
                        new ProductNotFoundException("Product not found with id : " + productId));

        return mapToResponse(product);
    }

    @Override
    public ProductResponse updateProduct(Long productId, ProductRequest request) {

        logger.info("Updating product with ID: {}", productId);

        Product product = productRepository.findById(productId)
                .orElseThrow(() ->
                        new ProductNotFoundException("Product not found with id : " + productId));

        product.setProductName(request.getProductName());
        product.setDescription(request.getDescription());
        product.setBrand(request.getBrand());
        product.setCategory(request.getCategory());
        product.setPrice(request.getPrice());
        product.setStock(request.getStock());
        product.setImageUrl(request.getImageUrl());
        product.setActive(request.getActive());

        Product updatedProduct = productRepository.save(product);

        logger.info("Product updated successfully.");

        return mapToResponse(updatedProduct);
    }

    @Override
    public void deleteProduct(Long productId) {

        logger.info("Deleting product with ID: {}", productId);

        Product product = productRepository.findById(productId)
                .orElseThrow(() ->
                        new ProductNotFoundException("Product not found with id : " + productId));

        productRepository.delete(product);

        logger.info("Product deleted successfully.");
    }

    @Override
    public List<ProductResponse> getProductsByCategory(String category) {

        logger.info("Fetching products by category: {}", category);

        return productRepository.findByCategory(category)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductResponse> searchProductsByName(String productName) {

        logger.info("Searching products with name: {}", productName);

        return productRepository.findByProductNameContainingIgnoreCase(productName)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }
    
    @Override
    public String purchaseProduct(PurchaseRequestDTO request) {

        logger.info("Processing purchase request...");

        // Check product
        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() ->
                        new ProductNotFoundException("Product not found with id : " + request.getProductId()));

        // Check stock
        if (product.getStock() <= 0) {
            throw new RuntimeException("Product Out of Stock");
        }

        // Validate user using User Service
        UserDTO user = userServiceClient.getUserById(request.getUserId());

        if (user == null) {
            throw new RuntimeException("User not found");
        }

        // Create Purchase DTO
        PurchaseDTO purchase = new PurchaseDTO();

        purchase.setUserId(user.getUserId());
        purchase.setProductId(product.getProductId());
        purchase.setTotalAmount(product.getPrice());
        purchase.setEmiDuration(request.getEmiDuration());
        purchase.setEmiAmount(product.getPrice() / request.getEmiDuration());
        purchase.setPurchaseDate(LocalDate.now());
        purchase.setStatus("ACTIVE");

        // Call Finance Service
        financeServiceClient.createPurchase(purchase);

        // Update Stock
        product.setStock(product.getStock() - 1);
        productRepository.save(product);

        logger.info("Purchase completed successfully.");

        return "Purchase Successful";
    }

    private ProductResponse mapToResponse(Product product) {

        return ProductResponse.builder()
                .productId(product.getProductId())
                .productName(product.getProductName())
                .description(product.getDescription())
                .brand(product.getBrand())
                .category(product.getCategory())
                .price(product.getPrice())
                .stock(product.getStock())
                .imageUrl(product.getImageUrl())
                .active(product.getActive())
                .build();
    }
}