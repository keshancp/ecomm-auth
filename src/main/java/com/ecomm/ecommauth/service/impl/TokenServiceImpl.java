package com.ecomm.ecommauth.service.impl;

import com.ecomm.ecommauth.service.TokenService;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;

@Service
public class TokenServiceImpl implements TokenService {

    @Override
    public boolean validateAccessToken(String accessToken) throws AuthenticationException {
        return false;
    }

    @Override
    public boolean validateRefreshToken(String refreshToken) throws AuthenticationException {
        return false;
    }
}
