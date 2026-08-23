package com.shivdattTrust.bank.security;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import org.springframework.beans.factory.annotation.Value;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.validation.Valid;


@Component
public class JwtUtil {

	//@Value("${neobank.jwt.secret}")
	@Valid
	private String secret;
	
	//@Value("${neobank.jwt.expiration-ms}")
	@Valid
	private long expirationMs;
	
	private SecretKey signingKey() {
		return Keys.hmacShaKeyFor(secret.getBytes());
	}
	
	public String generateToken(UserDetails userDetails) {
		Date now = new Date();
		Date expiry = new Date(now.getTime() + expirationMs);
		
		return Jwts.builder()
				.subject(userDetails.getUsername())
				.issuedAt(now)
				.expiration(expiry)
				.signWith(signingKey())
				.compact();
	}
	
	public String extractUsername(String token) {
		return extractClaim(token, Claims::getSubject);
				
	}
	
	public boolean isTokenValid(String token, UserDetails userDetails) {
		
		final String username = extractUsername(token);
		return username.equals(userDetails.getUsername()) && !isTokenExpired(token);

	}
	
	private boolean isTokenExpired(String token) {
        return extractClaim(token, Claims::getExpiration).before(new Date());
    }
	
	private <T> T extractClaim(String token, Function<Claims, T> resolver)
	{
		Claims claims = Jwts.parser()
				.verifyWith(signingKey())
				.build()
				.parseSignedClaims(token)
				.getPayload();
		
		return resolver.apply(claims);
		
	}
	
}
