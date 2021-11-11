package com.kishor.blog.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.kishor.blog.exception.BlogApiException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JwtTokenProvider {

	@Value("${app.jwt-secret}")
	private String jwtSecret;

	@Value("${app.jwt-expiration-milliseconds}")
	private int jwtExpirationInMs;

	// Generate token
	public String genarateToken(Authentication authentication) {
		String username = authentication.getName();
		Date current = new Date();
		Date expirationDate = new Date(current.getTime() + jwtExpirationInMs);
		String jwtToken = Jwts.builder().setSubject(username).setIssuedAt(new Date()).setExpiration(expirationDate)
				.signWith(SignatureAlgorithm.HS512, jwtSecret).compact();
		return jwtToken;
	}

	// Get username from the token
	public String getUsernameFromJwtToken(String jwtToken) {
		Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(jwtToken).getBody();
		return claims.getSubject();
	}

	// Validate Jwt token
	public boolean validateToken(String token) {
		try {
			Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
			return true;
		} catch (SignatureException signatureException) {
			throw new BlogApiException(HttpStatus.BAD_GATEWAY, "Invalid JWT signature.");
		} catch (MalformedJwtException malformedJwtException) {
			throw new BlogApiException(HttpStatus.BAD_GATEWAY, "Invalid JWT token.");
		} catch (ExpiredJwtException expiredJwtException) {
			throw new BlogApiException(HttpStatus.BAD_GATEWAY, "Expired JWT token.");
		} catch (UnsupportedJwtException unsupportedJwtException) {
			throw new BlogApiException(HttpStatus.BAD_GATEWAY, "Unsupported JWT signature.");
		} catch (IllegalArgumentException illegalArgumentException) {
			throw new BlogApiException(HttpStatus.BAD_GATEWAY, "JWT claims string is empty.");
		}
	}
}
