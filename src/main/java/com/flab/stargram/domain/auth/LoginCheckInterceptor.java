package com.flab.stargram.domain.auth;

import java.security.KeyPair;
import java.security.PublicKey;

import org.springframework.web.servlet.HandlerInterceptor;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class LoginCheckInterceptor implements HandlerInterceptor {
    private final PublicKey publicKey;

    public LoginCheckInterceptor(KeyPair keyPair) {
        this.publicKey = keyPair.getPublic();
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("Authorization");

        if (token == null || !token.startsWith("Bearer ")) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }

        try {
            Claims claims = Jwts.parser()
                .verifyWith(publicKey)
                .build()
                .parseSignedClaims(token.substring(7))
                .getPayload();

            request.setAttribute("userId", claims.getSubject());
        } catch (ExpiredJwtException e) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }

        return true;
    }

}
