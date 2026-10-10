package com.coforge.training.admin.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.coforge.training.admin.dto.PurchaseDTO;

@FeignClient(name = "FINANCE-SERVICE")
public interface FinanceServiceClient {

    @GetMapping("/api/purchases")
    List<PurchaseDTO> getAllPurchases();

    @GetMapping("/api/purchases/{id}")
    PurchaseDTO getPurchaseById(@PathVariable Long id);

}