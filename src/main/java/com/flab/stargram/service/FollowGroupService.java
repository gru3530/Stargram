package com.flab.stargram.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.flab.stargram.entity.dto.FollowDto;
import com.flab.stargram.entity.model.FollowGroup;
import com.flab.stargram.repository.FollowGroupRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FollowGroupService {
    private final FollowGroupRepository followGroupRepository;

    public boolean hasFollowing(Long userId) {
        return followGroupRepository.existsByFollowingId(userId);
    }

    public boolean hasFollower(Long userId) {
        return followGroupRepository.existsByFollowerId(userId);
    }

    @Transactional
    public FollowGroup getOrCreateFollowGroup(FollowDto followDto) {
        FollowGroup followGroup = followGroupRepository.findByFollowingId(followDto.getFollowingId());
        if (followGroup == null) {
            followGroup = createFollowGroup(followDto);
        }
        return followGroup;
    }

    private FollowGroup createFollowGroup(FollowDto followDto) {
        FollowGroup followGroup = FollowGroup.create(followDto.getFollowingId());
        return followGroupRepository.save(followGroup);
    }
}
