package com.flab.stargram.service;

import org.springframework.stereotype.Service;

import com.flab.stargram.entity.model.FollowGroup;
import com.flab.stargram.repository.FollowGroupRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FollowGroupService {
    private final FollowGroupRepository followGroupRepository;

    public boolean hasFollow(Long userId) {
        return followGroupRepository.existsByFollowingId(userId);
    }

    public FollowGroup getOrCreateFollowGroup(long followingId) {
        FollowGroup followGroup = followGroupRepository.findByFollowingId(followingId);
        if (followGroup == null) {
            followGroup = createFollowGroup(followingId);
        }
        return followGroup;
    }

    private FollowGroup createFollowGroup(long followingId) {
        FollowGroup followGroup = FollowGroup.create(followingId);
        return followGroupRepository.save(followGroup);
    }
}
