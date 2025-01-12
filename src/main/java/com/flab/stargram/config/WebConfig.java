package com.flab.stargram.config;

import java.security.KeyPair;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.flab.stargram.domain.auth.LoginCheckInterceptor;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    private final KeyPair keyPair;

    public WebConfig(KeyPair keyPair) {
        this.keyPair = keyPair;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginCheckInterceptor(keyPair))
            .order(1)
            .addPathPatterns("/users/logout");
    }
}
