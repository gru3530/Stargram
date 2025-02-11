package com.flab.stargram.entity.dto;

import com.flab.stargram.entity.common.ParseUtil;

import lombok.Getter;

@Getter
public class FollowPair {
    private Long followerId;
    private Long followingId;

    private FollowPair(Long followerId, Long followingId) {
        this.followerId = followerId;
        this.followingId = followingId;
    }

    public static FollowPair createFollowPairOf(String followerId, FollowRequestDto followRequestDto) {
        return new FollowPair(ParseUtil.parseToLong(followerId), followRequestDto.getFollowingId());
    }
}
