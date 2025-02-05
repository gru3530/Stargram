package com.flab.stargram.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.flab.stargram.config.exception.DataNotFoundException;
import com.flab.stargram.config.exception.DuplicateException;
import com.flab.stargram.entity.common.ApiResponseEnum;
import com.flab.stargram.entity.model.Follow;
import com.flab.stargram.entity.model.FollowPair;
import com.flab.stargram.repository.FollowRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FollowService {
    private final FollowRepository followRepository;
    private final UserService userService;
    private final FollowGroupService followGroupService;

    @Transactional
    public void followUser(FollowPair follow) {
        validateFollowPair(follow);
        checkDuplicateFollow(follow);

        followRepository.save(Follow.createFollowOf(followGroupService.getOrCreateFollowGroup(follow.getFollowingId()), follow));
    }

    @Transactional
    public void unfollowUser(FollowPair followPair) {
        validateFollowPair(followPair);
        validateFollowing(followPair);

        deleteFollow(followPair);
    }

    @Transactional
    public List<Follow> getFollowers(Long userId) {
        if (!userService.hasUserId(userId)) {
            throw new DataNotFoundException(ApiResponseEnum.USER_NOT_FOUND);
        }

        if(!followGroupService.hasFollow(userId)) {
            throw new DataNotFoundException(ApiResponseEnum.FOLLOW_NOT_FOUND);
        }

        return followRepository.findByFollowerId(userId);
    }

    private void validateFollowPair(FollowPair followPair) {
        userService.hasUserId(followPair.getFollowerId());
        userService.hasUserId(followPair.getFollowingId());
    }

    private void checkDuplicateFollow(FollowPair followPair) {
        if (hasFollow(followPair)) {
            throw new DuplicateException(ApiResponseEnum.ALREADY_FOLLOWING);
        }

        if(followPair.getFollowerId().equals(followPair.getFollowingId())) {
            throw new DuplicateException(ApiResponseEnum.ALREADY_FOLLOWING);
        }
    }

    private boolean hasFollow(FollowPair followPair) {
        return followRepository.existsByFollowerIdAndFollowingId(followPair.getFollowerId(),
            followPair.getFollowingId());
    }

    private void validateFollowing(FollowPair followPair) {
        if (!hasFollow(followPair)) {
            throw new DataNotFoundException(ApiResponseEnum.FOLLOW_NOT_FOUND);
        }
    }

    private void deleteFollow(FollowPair followPair) {
        followRepository.deleteByFollowerIdAndFollowingId(followPair.getFollowerId(), followPair.getFollowingId());
    }
}