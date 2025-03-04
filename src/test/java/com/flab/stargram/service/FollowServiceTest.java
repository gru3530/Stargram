package com.flab.stargram.service;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.flab.stargram.config.exception.DuplicateException;
import com.flab.stargram.entity.common.ApiResponseEnum;
import com.flab.stargram.entity.dto.FollowDto;
import com.flab.stargram.entity.model.Follow;
import com.flab.stargram.entity.model.FollowGroup;
import com.flab.stargram.repository.FollowRepository;
import com.flab.stargram.repository.UserRepository;

class FollowServiceTest {
    @InjectMocks
    private FollowService followService;

    @Mock
    private UserService userService;
    @Mock
    private FollowGroupService followGroupService;
    @Mock
    private FollowRepository followRepository;
    @Mock
    private UserRepository userRepository;

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

    @DisplayName("Follow API  Follow 에러 반환 테스트")
    @Test
    void followUser_error_duplicate() {
        //given
        FollowDto followDto = FollowDto.builder()
            .followingId(1L)
            .followerId(2L)
            .build();

        //when
        when(userRepository.existsById(followDto.getFollowerId())).thenReturn(true);
        when(followRepository.existsByFollowerIdAndFollowingId(followDto.getFollowerId(),
                followDto.getFollowingId())).thenReturn(true);
        //then
        assertThatThrownBy(() -> followService.followUser(followDto))
            .usingRecursiveComparison()
            .isEqualTo(new DuplicateException(ApiResponseEnum.ALREADY_FOLLOWING));
    }

    @DisplayName("follow API followingID와 followID 동일한 경우 에러 반환 테스트")
    @Test
    void followUser_error_InvalidFollow() {
        //given
        FollowDto followDto = FollowDto.builder()
            .followingId(1L)
            .followerId(1L)
            .build();

        //when
        when(userRepository.existsById(followDto.getFollowerId())).thenReturn(true);
        when(followRepository.existsByFollowerIdAndFollowingId(followDto.getFollowerId(),
            followDto.getFollowingId())).thenReturn(false);

        //then
        assertThatThrownBy(() -> followService.followUser(followDto))
            .usingRecursiveComparison()
            .isEqualTo(new DuplicateException(ApiResponseEnum.ALREADY_FOLLOWING));
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
    void getFollowerIds() {
        //given
        Long userId = 1L;
        List<Follow> mockFollowers = Collections.singletonList(mock(Follow.class));

        //when
        when(userService.hasUserId(userId)).thenReturn(true);
        when(followRepository.findByFollowerId(userId)).thenReturn(mockFollowers);
        List<Long> follow = followService.getFollowerIds(userId);

        //then
        assertNotNull(follow);
        verify(followRepository, times(1)).findByFollowerId(userId);
    }

    @DisplayName("특정 유저의 팔로잉들을 불러오는 API 통합테스트")
    @Test
    void getFollowings() {
        //given
        Long userId = 1L;
        List<Follow> mockFollowings = Collections.singletonList(mock(Follow.class));

        //when
        when(userService.hasUserId(userId)).thenReturn(true);
        when(followRepository.findByFollowingId(userId)).thenReturn(mockFollowings);
        List<Long> follow = followService.getFollowingIds(userId);

        //then
        assertNotNull(follow);
        verify(followRepository, times(1)).findByFollowingId(userId);
    }
}