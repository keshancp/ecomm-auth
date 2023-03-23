package com.ecomm.ecommauth.exception;

public enum AuthError {

    ERROR_LOGIN(500, "Invalied email"),
    ERROR_EMAIL_NOT_MATCH(500, "Please enter valid email address"),
    ERROR_PASSWORD_NOT_MATCH(500, "Please enter correct password");

    private int code;
    private String description;

    private AuthError(int code, String description) {
        this.code = code;
        this.description = description;
    }
    public int getCode() {
        return this.code;
    }

    public String getDescription() {
        return this.description;
    }
}
