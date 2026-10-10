package com.coforge.training.admin.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import com.coforge.training.admin.dto.UserDTO;

@FeignClient(name = "USER-SERVICE")
public interface UserServiceClient {

    @GetMapping("/users")
    List<UserDTO> getAllUsers();

    @GetMapping("/users/{id}")
    UserDTO getUserById(@PathVariable Long id);

    // Activate User
    @PutMapping("/users/activate/{id}")
    UserDTO activateUser(@PathVariable Long id);

}