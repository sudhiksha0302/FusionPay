package com.finance.productservice.dto;

import lombok.Data;

@Data
public class UserDTO {

    private Long userId;

    private String name;

    private String email;

    private String username;

    private Boolean verified;

    private Boolean activated;

}