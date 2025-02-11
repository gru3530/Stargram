package com.flab.stargram.domain.auth.service;

import org.springframework.stereotype.Service;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class AuthCookieService {
    private static final int MAX_AGE = 3600;
    private static final String COOKIE_NAME = "Authorization";
    private static final String COOKIE_PATH = "/";

    public void addAuthCookie(HttpServletResponse response, String token) {
        Cookie cookie = new Cookie(COOKIE_NAME, token);
        cookie.setHttpOnly(true);
        cookie.setPath(COOKIE_PATH);
        cookie.setMaxAge(MAX_AGE);
        response.addCookie(cookie);
    }

    public void removeAuthCookie(HttpServletResponse response) {
        Cookie cookie = new Cookie(COOKIE_NAME, null);
        cookie.setHttpOnly(true);
        cookie.setPath(COOKIE_PATH);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
    }
}
