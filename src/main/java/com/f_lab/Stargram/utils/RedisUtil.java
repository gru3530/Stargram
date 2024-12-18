package com.f_lab.Stargram.utils;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.time.Duration;

@RequiredArgsConstructor
@Service
public class RedisUtil {

    private final StringRedisTemplate redisTemplate;

    // 값 가져오기
    public String get(String key) {
        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
        return valueOperations.get(key);
    }

    // 키 존재 여부 확인
    public boolean hasKey(String key) {
        return redisTemplate.hasKey(key);
    }

    // 값 저장 (만료 시간 포함)
    public void setWithDuration(String key, String value, long durationInSeconds) {
        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
        valueOperations.set(key, value, Duration.ofSeconds(durationInSeconds));
    }

    // 값 삭제
    public void delete(String key) {
        redisTemplate.delete(key);
    }
}