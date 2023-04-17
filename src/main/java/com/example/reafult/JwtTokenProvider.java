package com.example.reafult;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;

import org.apache.log4j.spi.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.example.reafult.controller.SalesController;
import com.example.reafult.entities.CustomUserDetails;

import java.security.SignatureException;
import java.util.Date;
import java.util.logging.Logger;

@Component
public class JwtTokenProvider {

	@Value("${jwt.secret}")
	private String secretKey;

	@Value("${jwt.expiration}")
	private long validityInMilliseconds;

	/*
	 * private Logger logger = Logger.getLogger(secretKey, JwtTokenProvider.class);
	 */
	public String createToken(CustomUserDetails userDetails) {
		Date now = new Date();
		Date validity = new Date(now.getTime() + validityInMilliseconds);

		return Jwts.builder().setSubject(userDetails.getUsername()).setIssuedAt(now).setExpiration(validity)
				.signWith(SignatureAlgorithm.HS512, secretKey.getBytes()).compact();
	}

	public boolean validateToken(String authToken) {

		try {
			Jwts.parser().setSigningKey(secretKey.getBytes()).parseClaimsJws(authToken);
			return true;
		} catch (MalformedJwtException e) {
			e.getMessage();
			return false;
		} catch (ExpiredJwtException e) {
			e.getMessage();
			return false;
		} catch (UnsupportedJwtException e) {
			e.getMessage();
			return false;
		} catch (IllegalArgumentException e) {
			e.getMessage();
			return false;
		}

	}

	public String getUsernameFromToken(String token) {
		Claims claims = Jwts.parser().setSigningKey(secretKey.getBytes()).parseClaimsJws(token).getBody();
		return claims.getSubject();
	}

	public Date getExpirationDateFromToken(String token) {
		Claims claims = Jwts.parser().setSigningKey(secretKey.getBytes()).parseClaimsJws(token).getBody();
		return claims.getExpiration();
	}
}
