package com.coforge.training.userservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BankResponseDTO {

    private Long bankId;

    private String bankName;

    private String accountNumber;

    private String ifscCode;

}