package com.finance.productservice.service;

import java.util.List;

import com.finance.productservice.dto.ProductRequest;
import com.finance.productservice.dto.ProductResponse;
import com.finance.productservice.dto.PurchaseDTO;
import com.finance.productservice.dto.PurchaseRequestDTO;

public interface ProductService {

    ProductResponse addProduct(ProductRequest productRequest);

    List<ProductResponse> getAllProducts();

    ProductResponse getProductById(Long productId);

    ProductResponse updateProduct(Long productId, ProductRequest productRequest);

    void deleteProduct(Long productId);

    List<ProductResponse> getProductsByCategory(String category);

    List<ProductResponse> searchProductsByName(String productName);
    
    String purchaseProduct(PurchaseRequestDTO request);
    


}