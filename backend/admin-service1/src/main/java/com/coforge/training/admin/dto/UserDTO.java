package com.coforge.training.admin.dto;

import lombok.Data;

@Data
public class UserDTO {

    private Long userId;
    private String name;
    private String username;
    private String email;
    private String phoneNumber;
    private Boolean verified;
    private Boolean activated;

}