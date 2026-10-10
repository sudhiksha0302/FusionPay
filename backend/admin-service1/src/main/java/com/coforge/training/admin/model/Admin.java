package com.coforge.training.admin.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Table(name = "admins")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long adminId;

    @NotBlank(message = "Admin Name is required")
    @Column(nullable = false)
    private String adminName;

    @NotBlank(message = "Username is required")
    @Column(nullable = false, unique = true)
    private String username;

    @Email(message = "Invalid Email")
    @Column(unique = true)
    private String email;

    @NotBlank(message = "Password is required")
    @Column(nullable = false)
    private String password;

}