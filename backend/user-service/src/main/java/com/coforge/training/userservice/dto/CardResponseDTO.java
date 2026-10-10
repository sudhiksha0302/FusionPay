package com.coforge.training.userservice.dto;

import java.time.LocalDate;

import com.coforge.training.userservice.enums.CardStatus;
import com.coforge.training.userservice.enums.CardType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CardResponseDTO {

    private Long cardId;

    private CardType cardType;

    private String cardNumber;

    private Double joiningFee;

    private Double totalLimit;

    private Double availableLimit;

    private LocalDate expiryDate;

    private CardStatus cardStatus;

}