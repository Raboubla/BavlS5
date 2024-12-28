package com.example.Bavl.security;

import com.example.Bavl.entities.Client;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtService {
    private final String SECRET_KEY = "your_secret_key_here";
    private final long EXPIRATION_TIME = 864_000_000; // 10 days

    public String generateToken(Client client) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("email", client.getEmail());
        
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(client.getEmail())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();
    }
} 