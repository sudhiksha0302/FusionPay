package com.coforge.training.userservice.entity;

import java.time.LocalDate;

import com.coforge.training.userservice.enums.CardStatus;
import com.coforge.training.userservice.enums.CardType;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "card_details")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CardDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cardId;

    @Enumerated(EnumType.STRING)
    private CardType cardType;

    @Column(unique = true)
    private String cardNumber;

    private Double joiningFee;

    private Double totalLimit;

    private Double availableLimit;

    private LocalDate expiryDate;

    @Enumerated(EnumType.STRING)
    private CardStatus cardStatus;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

}