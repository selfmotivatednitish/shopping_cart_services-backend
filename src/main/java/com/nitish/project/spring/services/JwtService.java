package com.nitish.project.spring.services;

import org.springframework.security.core.userdetails.UserDetails;

public interface JwtService {
	public String extractUsername(String token);
	public Boolean validateToken(String token, UserDetails userDetails);
	public String generateToken(String username);
}
