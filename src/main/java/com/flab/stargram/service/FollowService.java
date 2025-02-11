package com.flab.stargram.service;

import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.flab.stargram.config.exception.DataNotFoundException;
import com.flab.stargram.config.exception.DuplicateException;
import com.flab.stargram.entity.common.ApiResponseEnum;
import com.flab.stargram.entity.dto.FollowDto;
import com.flab.stargram.entity.model.Follow;
import com.flab.stargram.repository.FollowRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FollowService {
    private final FollowRepository followRepository;
    private final UserService userService;
    private final FollowGroupService followGroupService;

    @Transactional
    public void followUser(FollowDto follow) {
        validateFollowDto(follow);
        checkDuplicateFollow(follow);

        followRepository.save(Follow.createFollowOf(followGroupService.getOrCreateFollowGroup(follow), follow));
    }

    @Transactional
    public void unfollowUser(FollowDto followDto) {
        validateFollowDto(followDto);
        validateFollowing(followDto);

        deleteFollow(followDto);
    }

    @Transactional
    public List<Long> getFollowerIds(Long userId) {
        if (!userService.hasUserId(userId)) {
            throw new DataNotFoundException(ApiResponseEnum.USER_NOT_FOUND);
        }

        List<Follow> followerIds = followRepository.findByFollowerId(userId);
        return followerIds.isEmpty() ? Collections.emptyList() : getFollowerIds(followerIds);
    }

    @Transactional
    public List<Long> getFollowingIds(Long userId) {
        if (!userService.hasUserId(userId)) {
            throw new DataNotFoundException(ApiResponseEnum.USER_NOT_FOUND);
        }

        List<Follow> followingIds = followRepository.findByFollowingId(userId);
        return followingIds.isEmpty() ? Collections.emptyList() : getFollowingIds(followingIds);
    }

    private void validateFollowDto(FollowDto followDto) {
        userService.hasUserId(followDto.getFollowerId());
        userService.hasUserId(followDto.getFollowingId());
    }

    private void checkDuplicateFollow(FollowDto followDto) {
        if (hasFollow(followDto)) {
            throw new DuplicateException(ApiResponseEnum.ALREADY_FOLLOWING);
        }

        if (followDto.isSameUser()) {
            throw new DuplicateException(ApiResponseEnum.ALREADY_FOLLOWING);
        }
    }

    private boolean hasFollow(FollowDto followDto) {
        return followRepository.existsByFollowerIdAndFollowingId(followDto.getFollowerId(),
            followDto.getFollowingId());
    }

    private void validateFollowing(FollowDto followDto) {
        if (!hasFollow(followDto)) {
            throw new DataNotFoundException(ApiResponseEnum.FOLLOW_NOT_FOUND);
        }
    }

    private void deleteFollow(FollowDto followDto) {
        followRepository.deleteByFollowerIdAndFollowingId(followDto.getFollowerId(), followDto.getFollowingId());
    }

    private List<Long> getFollowerIds(List<Follow> follows) {
        return follows.stream()
            .map(Follow::getFollowerId)
            .toList();
    }

    public List<Long> getFollowingIds(List<Follow> follows) {
        return follows.stream()
            .map(Follow::getFollowingId)
            .toList();
    }
}