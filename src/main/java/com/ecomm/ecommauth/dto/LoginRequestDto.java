package com.ecomm.ecommauth.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequestDto {

    @NotEmpty(message = "User name cannot be empty")
    private String userName;

    @NotEmpty(message = "password cannot be empty")
    private String password;


}
