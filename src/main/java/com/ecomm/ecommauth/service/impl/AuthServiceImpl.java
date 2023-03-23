package com.ecomm.ecommauth.service.impl;

import com.ecomm.ecommauth.dto.JWTResponse;
import com.ecomm.ecommauth.dto.LoginRequestDto;
import com.ecomm.ecommauth.exception.AuthServiceException;
import com.ecomm.ecommauth.service.AuthService;
import com.ecomm.ecommauth.util.AuthUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private AuthUtil authUtil;


    @Override
    public JWTResponse login(LoginRequestDto loginRequestDto,String traceId) throws AuthServiceException {


        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequestDto.getUserName(),
                        loginRequestDto.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String accessToken = authUtil.generateToken(loginRequestDto.getUserName());
        String refreshToken = authUtil.createRefreshToken(loginRequestDto.getUserName());

        return new JWTResponse(accessToken,refreshToken);
    }
}
