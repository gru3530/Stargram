package com.flab.stargram.domain.fanout.strategy;

import com.flab.stargram.entity.model.Post;

public interface FanoutStrategy {
    void distributeToFollowers(Post post);
}
