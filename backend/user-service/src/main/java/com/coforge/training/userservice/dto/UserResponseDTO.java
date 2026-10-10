package com.coforge.training.userservice.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDTO {

    private Long userId;
    private String name;
    private String email;
    private String username;
    private String phoneNumber;
    private String address;
    private boolean verified;
    private boolean activated;
    private LocalDate dateOfBirth;

}