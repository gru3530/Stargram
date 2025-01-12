package com.flab.stargram.domain.auth.service;

import java.security.Key;

import org.springframework.stereotype.Service;
import com.flab.stargram.entity.model.User;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Date;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;

@Service
public class AuthService {
    private final long testJwtExpiration = 3600;
    private final PublicKey publicKey;
    private final PrivateKey privateKey;

    public AuthService(KeyPair keyPair) {
        this.privateKey = keyPair.getPrivate();
        this.publicKey = keyPair.getPublic();
    }


    public String generateToken(User user) {
        Date date = new Date();
        return Jwts.builder()
            .subject(user.getId().toString())
            .claim("id", user.getId())
            .issuedAt(date)
            .expiration(new Date(date.getTime() + testJwtExpiration))
            .signWith(privateKey)
            .compact();
    }

    public Claims validateToken(String token) {
        try {
            return Jwts.parser()
                .verifyWith(publicKey)
                .build()
                .parseSignedClaims(token)
                .getPayload();
        } catch (JwtException e) {
            throw new IllegalStateException("Invalid Token", e);
        }
    }

}
