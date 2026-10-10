package com.finance.productservice.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.finance.productservice.dto.UserDTO;

@FeignClient(name = "USER-SERVICE")
public interface UserServiceClient {

    @GetMapping("/users/{id}")
    UserDTO getUserById(@PathVariable("id") Long id);

}