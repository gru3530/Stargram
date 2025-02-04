package com.flab.stargram.entity.model;

import lombok.Getter;

@Getter
public class FollowPair {
    private Long followerId;
    private Long followingId;

    private FollowPair(Long followerId, Long followingId) {
        this.followerId = followerId;
        this.followingId = followingId;
    }

    public static FollowPair createFollowPairOf(Long followerId, Long followingId) {
        return new FollowPair(followerId, followingId);
    }
}
