package com.ecomm.ecommauth.service;

import com.ecomm.ecommauth.dto.JWTResponse;
import com.ecomm.ecommauth.dto.LoginRequestDto;
import com.ecomm.ecommauth.exception.AuthServiceException;

public interface AuthService {

    JWTResponse login(LoginRequestDto loginRequestDto,String traceId) throws AuthServiceException;
}
