package com.flab.stargram.service;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.flab.stargram.entity.dto.FollowPair;
import com.flab.stargram.entity.model.Follow;
import com.flab.stargram.entity.model.FollowGroup;
import com.flab.stargram.repository.FollowGroupRepository;
import com.flab.stargram.repository.FollowRepository;

class FollowServiceTest {
    @InjectMocks
    private FollowService followService;

    @Mock
    private UserService userService;
    @Mock
    private FollowGroupService followGroupService;
    @Mock
    private FollowRepository followRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void followUser() {
        //given
        FollowPair followPair = FollowPair.builder()
            .followingId(1L)
            .followerId(2L)
            .build();

        //when
        when(userService.hasUserId(followPair.getFollowerId())).thenReturn(true);
        when(userService.hasUserId(followPair.getFollowingId())).thenReturn(true);
        when(followGroupService.getOrCreateFollowGroup(followPair.getFollowingId())).thenReturn(mock(FollowGroup.class));
        when(followRepository.existsByFollowerIdAndFollowingId(followPair.getFollowerId(), followPair.getFollowingId())).thenReturn(false);

        followService.followUser(followPair);

        //then
        verify(followRepository, times(1)).save(any(Follow.class));
    }

    @Test
    void unfollowUser() {
    }

    @Test
    void getFollowers() {
    }
}