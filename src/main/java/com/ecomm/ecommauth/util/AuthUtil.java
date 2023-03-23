package com.ecomm.ecommauth.util;


import com.ecomm.ecommauth.entity.User;
import com.ecomm.ecommauth.exception.AuthServiceException;
import com.ecomm.ecommauth.service.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class AuthUtil {


	@Autowired
	private UserService userService;

	private String SECRET_KEY = "secret";

	private Map<String, Object> newClaim;

	public String extractUsername(String token) {
		return extractClaim(token, Claims::getSubject);
	}

	public Date extractExpiration(String token) {
		return extractClaim(token, Claims::getExpiration);
	}

	public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = extractAllClaims(token);
		return claimsResolver.apply(claims);
	}
	private Claims extractAllClaims(String token) {
		return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
	}

	private Boolean isTokenExpired(String token) {
		return extractExpiration(token).before(new Date());
	}

	public String generateToken(String username) throws AuthServiceException {
		Map<String, Object> claims = new HashMap<>();

		User user=userService.getUser(username);

		claims.put("roles",user.getUserRoleList());
		//claims.put("permissions",rolePermissionResponseDto.getPermissions());
		claims.put("user_name",user.getUserName());

		newClaim = claims;
		return createToken(claims, username);
	}

	private String createToken(Map<String, Object> claims, String subject) {

		return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
				.signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
	}

	public Boolean validateToken(String token, UserDetails userDetails) {
		final String username = extractUsername(token);
		return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}

	public String createRefreshToken(String subject) {
		return Jwts.builder().setClaims(newClaim).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 30))
				.signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
	}
}
