package com.coforge.training.userservice.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ForgotPasswordRequestDTO {

    @NotBlank
    private String username;

    @NotBlank
    private String newPassword;

}