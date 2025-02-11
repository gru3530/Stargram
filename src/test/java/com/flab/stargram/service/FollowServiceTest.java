package com.flab.stargram.service;

import static org.mockito.Mockito.*;

import java.util.Collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
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

    @DisplayName("follow API 통합테스트")
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


    @DisplayName("unfollow API 통합테스트")
    @Test
    void unfollowUser() {
        //given
        FollowDto followDto = FollowDto.builder()
            .followingId(1L)
            .followerId(2L)
            .build();

        //when
        when(userService.hasUserId(followDto.getFollowerId())).thenReturn(true);
        when(userService.hasUserId(followDto.getFollowingId())).thenReturn(true);
        when(followRepository.existsByFollowerIdAndFollowingId(followDto.getFollowerId(),
            followDto.getFollowingId())).thenReturn(true);
        doNothing().when(followRepository).deleteByFollowerIdAndFollowingId(followDto.getFollowerId(), followDto.getFollowingId());

        followService.unfollowUser(followDto);

        //then
        verify(followRepository, times(1)).deleteByFollowerIdAndFollowingId(followDto.getFollowerId(), followDto.getFollowingId());
    }


    @DisplayName("특정 유저의 팔로워 들을 불러오는 API 통합테스트")
    @Test
    void getFollowers() {
        //given
        Long userId = 1L;

        //then
        when(userService.hasUserId(userId)).thenReturn(true);
        when(followGroupService.hasFollow(userId)).thenReturn(true);
        when(followRepository.findByFollowerId(userId)).thenReturn(Collections.emptyList());

        followService.getFollowers(userId);

        //then
        verify(followRepository, times(1)).findByFollowerId(userId);
    }
}