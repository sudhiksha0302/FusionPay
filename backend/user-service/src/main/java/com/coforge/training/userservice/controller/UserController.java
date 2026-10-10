package com.coforge.training.userservice.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.coforge.training.userservice.dto.BankResponseDTO;
import com.coforge.training.userservice.dto.CardResponseDTO;
import com.coforge.training.userservice.dto.ChangePasswordRequestDTO;
import com.coforge.training.userservice.dto.ForgotPasswordRequestDTO;
import com.coforge.training.userservice.dto.LoginRequestDTO;
import com.coforge.training.userservice.dto.LoginResponseDTO;
import com.coforge.training.userservice.dto.RegisterRequestDTO;
import com.coforge.training.userservice.dto.SendOtpRequestDTO;
import com.coforge.training.userservice.dto.UserResponseDTO;
import com.coforge.training.userservice.dto.VerifyOtpRequestDTO;
import com.coforge.training.userservice.service.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Validated
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    private final UserService userService;

    // Register User
    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> register(
            @Valid @RequestBody RegisterRequestDTO request) {

        return new ResponseEntity<>(userService.register(request), HttpStatus.CREATED);
    }

    // Login User
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(
            @Valid @RequestBody LoginRequestDTO request) {

        return ResponseEntity.ok(userService.login(request));
    }

    // Get User By ID
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable Long id) {

        UserResponseDTO user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }
    @GetMapping("/{id}/bank")
    public ResponseEntity<BankResponseDTO> getBankDetails(
            @PathVariable Long id){

        return ResponseEntity.ok(
                userService.getBankDetails(id));

    }

    @GetMapping("/{id}/card")
    public ResponseEntity<CardResponseDTO> getCardDetails(
            @PathVariable Long id){

        return ResponseEntity.ok(
                userService.getCardDetails(id));

    }
    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> getAllUsers() {

        return ResponseEntity.ok(userService.getAllUsers());

    }
    @PostMapping("/forgot-password")
    public ResponseEntity<String> forgotPassword(
            @RequestBody ForgotPasswordRequestDTO request) {

        return ResponseEntity.ok(
                userService.forgotPassword(request));

    }
    
    @PutMapping("/activate/{id}")
    public ResponseEntity<UserResponseDTO> activateUser(@PathVariable Long id) {

        return ResponseEntity.ok(
                userService.activateUser(id));

    }
    @PostMapping("/send-otp")
    public ResponseEntity<String> sendOtp(
            @RequestBody SendOtpRequestDTO request) {

        return ResponseEntity.ok(userService.sendOtp(request));
    }

    @PostMapping("/verify-otp")
    public ResponseEntity<String> verifyOtp(
            @RequestBody VerifyOtpRequestDTO request) {

        return ResponseEntity.ok(userService.verifyOtp(request));
    }

    @PutMapping("/change-password")
    public ResponseEntity<String> changePassword(
            @RequestBody ChangePasswordRequestDTO request) {

        return ResponseEntity.ok(userService.changePassword(request));
    }
}