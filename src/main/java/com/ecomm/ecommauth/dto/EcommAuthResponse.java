package com.ecomm.ecommauth.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EcommAuthResponse {

    private int statusCode;
    private String message;
    private Object data;



}
