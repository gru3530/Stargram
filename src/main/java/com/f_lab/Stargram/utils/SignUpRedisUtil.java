package com.f_lab.Stargram.utils;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Component
@RequiredArgsConstructor
public class SignUpRedisUtil {

    private final StringRedisTemplate redisTemplate;

    public String get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    public void set(String key, String value, long durationInSeconds) {
        redisTemplate.opsForValue().set(key, value, Duration.ofSeconds(durationInSeconds));
    }

    public void delete(String key) {
        redisTemplate.delete(key);
    }
}
