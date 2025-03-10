package com.flab.stargram.domain.cache.Service;

import java.time.Duration;
import java.util.List;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.flab.stargram.entity.model.Post;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class RedisCacheService implements CacheService {
    private final RedisTemplate<String, Post> redisTemplate;

    @Override
    public void cachePostForInfluencer(Post post) {
        String key = "Influencer" + post.getUserId();
        redisTemplate.opsForList().leftPush(key, post);
        redisTemplate.expire(key, Duration.ofDays(7));
    }
}
