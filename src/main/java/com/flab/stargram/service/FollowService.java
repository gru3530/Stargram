package com.flab.stargram.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.flab.stargram.config.exception.DataNotFoundException;
import com.flab.stargram.config.exception.DuplicateException;
import com.flab.stargram.entity.common.ApiResponseEnum;
import com.flab.stargram.entity.model.Follow;
import com.flab.stargram.entity.model.FollowGroup;
import com.flab.stargram.entity.model.FollowPair;
import com.flab.stargram.repository.FollowGroupRepository;
import com.flab.stargram.repository.FollowRepository;
import com.flab.stargram.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FollowService {
    private final FollowRepository followRepository;
    private final FollowGroupRepository followGroupRepository;
    private final UserRepository userRepository;

    @Transactional
    public void followUser(FollowPair follow) {
        validateUserExists(follow);
        checkDuplicateFollow(follow);

        FollowGroup followGroup = getFollowGroup(follow);
        if (followGroup == null) {
            var group = FollowGroup.create(follow);
            followGroup = followGroupRepository.save(group);
        }

        followRepository.save(Follow.create(followGroup, follow));
    }

    @Transactional
    public void unfollowUser(FollowPair followPair) {
        validateUserExists(followPair);
        validateFollowing(followPair);

        unfollowAndSave(followPair);
    }

    private void unfollowAndSave(FollowPair followPair) {
        followRepository.deleteByFollowerIdAndFollowingId(followPair.getFollowerId(), followPair.getFollowingId());
    }

    private void validateUserExists(FollowPair followPair) {
        if (!userRepository.existsById(followPair.getFollowerId()) ||
            !userRepository.existsById(followPair.getFollowingId())) {
            throw new DataNotFoundException(ApiResponseEnum.USER_NOT_FOUND);
        }
    }

    private void checkDuplicateFollow(FollowPair followPair) {
        if (isfollowExists(followPair)) {
            throw new DuplicateException(ApiResponseEnum.ALREADY_FOLLOWING);
        }
    }

    private boolean isfollowExists(FollowPair followPair) {
        return followRepository.existsByFollowerIdAndFollowingId(followPair.getFollowerId(),
            followPair.getFollowingId());
    }

    private FollowGroup getFollowGroup(FollowPair followPair) {
        return followGroupRepository.findByUserId(followPair.getFollowerId());
    }

    private void validateFollowing(FollowPair followPair) {
        if (!isfollowExists(followPair)) {
            throw new DataNotFoundException(ApiResponseEnum.FOLLOW_NOT_FOUND);
        }
    }
}
