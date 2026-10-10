package com.finance.productservice.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.finance.productservice.dto.PurchaseDTO;

@FeignClient(name = "FINANCE-SERVICE")
public interface FinanceServiceClient {

    @PostMapping("/api/purchases")
    PurchaseDTO createPurchase(@RequestBody PurchaseDTO purchaseDTO);

}