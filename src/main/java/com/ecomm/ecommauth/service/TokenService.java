package com.ecomm.ecommauth.service;

import javax.naming.AuthenticationException;

public interface TokenService {

    boolean validateAccessToken(String accessToken) throws AuthenticationException;
    boolean validateRefreshToken(String refreshToken) throws AuthenticationException;
}
