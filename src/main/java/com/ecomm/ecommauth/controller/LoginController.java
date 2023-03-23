package com.ecomm.ecommauth.controller;

import com.ecomm.ecommauth.dto.EcommAuthResponse;
import com.ecomm.ecommauth.dto.JWTResponse;
import com.ecomm.ecommauth.dto.LoginRequestDto;
import com.ecomm.ecommauth.exception.AuthServiceException;
import com.ecomm.ecommauth.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class LoginController {

    @Autowired
    private AuthService authService;


    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public EcommAuthResponse login(@Valid @RequestBody LoginRequestDto loginRequestDto, @RequestParam String traceId) throws AuthServiceException {

        JWTResponse jwtResponse= authService.login(loginRequestDto,traceId);

        return new EcommAuthResponse(HttpStatus.ACCEPTED.value(),"Successfully logged",jwtResponse);
    }

}
