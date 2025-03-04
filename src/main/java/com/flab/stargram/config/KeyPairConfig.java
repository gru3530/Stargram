package com.flab.stargram.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.security.KeyPair;
import java.security.KeyPairGenerator;

@Configuration
public class KeyPairConfig {
    private static final String KEY_ALGORITHM = "RSA";
    private static final int RSA_KEY_SIZE = 2048;

    @Bean
    public KeyPair keyPair() throws Exception {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(KEY_ALGORITHM);
        keyPairGenerator.initialize(RSA_KEY_SIZE);
        return keyPairGenerator.generateKeyPair();
    }
}