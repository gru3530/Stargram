package com.f_lab.Stargram.utils;

import com.f_lab.Stargram.domain.user.model.Profile;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UserRedisUtil {

    private final StringRedisTemplate redisTemplate;

    public Set<Profile> getUsers(List<Long> userIds) {
        return userIds.stream()
                .map(userId -> redisTemplate.opsForValue().get("user:" + userId))
                .filter(json -> json != null)
                .map(json -> JsonUtil.deserialize(json, Profile.class))
                .collect(Collectors.toSet());
    }

    public void saveAll(Set<Profile> profiles) {
        profiles.forEach(profile -> {
            String serializedProfile = JsonUtil.serialize(profile);
            redisTemplate.opsForValue().set("user:" + profile.getUserId(), serializedProfile);
        });
    }
}
