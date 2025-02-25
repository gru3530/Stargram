package com.flab.stargram.domain.fanout.strategy;

import org.springframework.stereotype.Component;

import com.flab.stargram.domain.cache.Service.CacheService;
import com.flab.stargram.entity.model.Post;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component("INFLUENCER")
public class InfluencerFanoutStrategy implements FanoutStrategy {
    CacheService cacheService;

    @Override
    public void distributeToFollowers(Post post) {
        cacheService.cachePostForInfluencer(post);
    }
}
