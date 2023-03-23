package com.ecomm.ecommauth.filter;


import com.ecomm.ecommauth.exception.AuthError;
import com.ecomm.ecommauth.service.TokenService;
import com.ecomm.ecommauth.service.impl.UserServiceImpl;
import com.ecomm.ecommauth.util.AuthUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CustomAuthorizationFilter extends OncePerRequestFilter {

    @Autowired
    private AuthUtil authUtil;

    @Autowired
    private UserServiceImpl userServiceImpl;

    @Autowired
    private TokenService tokenService;

    @Override
	protected void doFilterInternal(HttpServletRequest req,
                                    HttpServletResponse res,
                                    FilterChain chain) throws IOException, ServletException, UsernameNotFoundException {
    	String authorizationHeader=req.getHeader("Authorization");
    	String token=null;
        String username=null;


    	if(authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
             token = authorizationHeader.substring("Bearer ".length());
             username=authUtil.extractUsername(token);
    	}

    	if(username != null && SecurityContextHolder.getContext().getAuthentication()==null){
            UserDetails userDetails;
            try {
                userDetails= userServiceImpl.loadUserByUsername(username);
            }
            catch (UsernameNotFoundException e){
                throw new UsernameNotFoundException(AuthError.ERROR_EMAIL_NOT_MATCH.getDescription());
            }

            if(authUtil.validateToken(token,userDetails)){
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken=
                        new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(req));

                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }


        chain.doFilter(req, res);

    }


}
