package com.flab.stargram.domain.auth.service;

import org.springframework.stereotype.Service;

import com.flab.stargram.domain.auth.util.AESUtil;
import com.flab.stargram.entity.model.User;

import java.util.Date;

import javax.crypto.SecretKey;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@Service
public class AuthService {
    private final long testJwtExpiration = 3600;
    private final SecretKey aesKey;

    public AuthService() throws Exception {
        this.aesKey = AESUtil.generateKey();
    }

    public String generateToken(User user){
        Date date = new Date();
        String token = Jwts.builder()
            .subject(user.getId().toString())
            .claim("userId", user.getId())
            .issuedAt(date)
            .expiration(new Date(date.getTime() + testJwtExpiration))
            .signWith(aesKey)
            .compact();

        return AESUtil.encrypt(token,aesKey);
    }

    public Claims validateToken(String encryptedToken){
        String decryptedJwt = AESUtil.decrypt(encryptedToken, aesKey);
        return Jwts.parser()
            .verifyWith(aesKey)
            .build()
            .parseSignedClaims(decryptedJwt)
            .getPayload();
    }

}
