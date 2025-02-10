package com.flab.stargram.service;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.flab.stargram.entity.dto.FollowDto;
import com.flab.stargram.entity.model.Follow;
import com.flab.stargram.entity.model.FollowGroup;
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
        FollowDto followDto = FollowDto.builder()
            .followingId(1L)
            .followerId(2L)
            .build();

        //when
        when(userService.hasUserId(followDto.getFollowerId())).thenReturn(true);
        when(userService.hasUserId(followDto.getFollowingId())).thenReturn(true);
        when(followGroupService.getOrCreateFollowGroup(followDto)).thenReturn(mock(FollowGroup.class));
        when(followRepository.existsByFollowerIdAndFollowingId(followDto.getFollowerId(), followDto.getFollowingId())).thenReturn(false);

        followService.followUser(followDto);

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