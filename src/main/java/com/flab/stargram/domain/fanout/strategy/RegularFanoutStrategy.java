package com.flab.stargram.domain.fanout.strategy;

import org.springframework.stereotype.Component;

import com.flab.stargram.entity.model.Post;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component("REGULAR")
public class RegularFanoutStrategy implements FanoutStrategy {

    @Override
    public void distributeToFollowers(Post post) {

    }
}
