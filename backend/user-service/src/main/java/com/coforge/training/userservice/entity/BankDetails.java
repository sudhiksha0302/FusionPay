package com.coforge.training.userservice.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "bank_details")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BankDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bankId;

    private String bankName;

    @Column(unique = true)
    private String accountNumber;

    private String ifscCode;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

}