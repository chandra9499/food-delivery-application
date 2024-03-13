package com.restaurants.config;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.crypto.SecretKey;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtProvider {

	private SecretKey key = Keys.hmacShaKeyFor(JwtConstant.SECRET_KEY.getBytes());
	
	public String genarateToken(Authentication auth) {
		Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();
		
		String roles = populateAuthorities(authorities);
		//jwt token does not allow us to store in GrantedAuthority format hance we store it in String format
		String jwt = Jwts.builder().setIssuedAt(new Date())
				.setExpiration(new Date(new Date().getTime()+86400000))
				.claim("email", auth.getName())
				.claim("authorities", roles)
				.signWith(key)
				.compact();
		return jwt;
		
	}
	
	
	public String getEmailFromJwtToken(String jwt) {
		
		jwt=jwt.substring(7);
		
		Claims claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jwt).getBody();
		
		String email = String.valueOf(claims.get("email"));
		return email;
		
	}
	
	public String populateAuthorities(Collection<? extends GrantedAuthority> authorities) {
		Set<String> auth = new HashSet<>();
		
		for(GrantedAuthority authority : authorities) {
			auth.add(authority.getAuthority());
		}
		
		return String.join(",", auth);
	}
}
