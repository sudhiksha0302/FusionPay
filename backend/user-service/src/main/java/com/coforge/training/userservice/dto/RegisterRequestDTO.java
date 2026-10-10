package com.coforge.training.userservice.dto;

import java.time.LocalDate;

import com.coforge.training.userservice.enums.CardType;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class RegisterRequestDTO {

    @NotBlank
    private String name;

    private LocalDate dateOfBirth;

    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String phoneNumber;

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    @NotBlank
    private String address;

    @NotBlank
    private String bankName;

    @NotBlank
    private String accountNumber;

    @NotBlank
    private String ifscCode;

    @NotNull
    private CardType cardType;
}