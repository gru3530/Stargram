package com.flab.stargram.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.flab.stargram.domain.auth.service.AuthInterceptorService;

import lombok.RequiredArgsConstructor;

@Profile("!test")
@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {

    private final AuthInterceptorService authInterceptorService;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authInterceptorService)
            .order(1)
            .addPathPatterns("/users/**");
    }
}
