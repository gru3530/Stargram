package com.flab.stargram.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.flab.stargram.config.exception.DataNotFoundException;
import com.flab.stargram.config.exception.DuplicateException;
import com.flab.stargram.entity.common.ApiResponseEnum;
import com.flab.stargram.entity.model.Follow;
import com.flab.stargram.entity.model.FollowGroup;
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
    public void followUser(Long followerId, Long followingId) {
        validateUserExists(followerId);
        validateUserExists(followingId);
        checkDuplicateFollow(followerId, followingId);

        FollowGroup followGroup = followGroupRepository.findByUserId(followerId);
        if (followGroup == null) {
            followGroup = followGroupRepository.save(FollowGroup.create(followerId));
        }

        followRepository.save(Follow.create(followGroup, followerId, followingId));
    }

    private void validateUserExists(Long userId) {
        if(!userRepository.existsById(userId)) {
            throw new DataNotFoundException(ApiResponseEnum.USER_NOT_FOUND);
        }
    }

    private void checkDuplicateFollow(Long followerId, Long followingId) {
        if (followRepository.existsByFollowerIdAndFollowingId(followerId, followingId)) {
            throw new DuplicateException(ApiResponseEnum.ALREADY_FOLLOWING);
        }
    }



}
