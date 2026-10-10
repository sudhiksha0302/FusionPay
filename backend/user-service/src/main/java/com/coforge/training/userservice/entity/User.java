package com.coforge.training.userservice.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(nullable = false)
    private String name;

    private LocalDate dateOfBirth;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, unique = true)
    private String phoneNumber;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String address;

    private boolean verified;

    private boolean activated;

    private LocalDateTime createdAt;

    @PrePersist
    public void prePersist() {
        createdAt = LocalDateTime.now();
    }

    @OneToOne(mappedBy = "user",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private BankDetails bankDetails;

    @OneToOne(mappedBy = "user",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private CardDetails cardDetails;
    
    @Column(length = 6)
    private String otp;

    private boolean otpVerified;

}