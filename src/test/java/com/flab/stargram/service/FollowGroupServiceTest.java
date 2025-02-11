package com.flab.stargram.service;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.flab.stargram.entity.dto.FollowDto;
import com.flab.stargram.entity.model.FollowGroup;
import com.flab.stargram.repository.FollowGroupRepository;

@ExtendWith(MockitoExtension.class)
class FollowGroupServiceTest {

    @InjectMocks
    private FollowGroupService followGroupService;

    @Mock
    private FollowGroupRepository followGroupRepository;

    private FollowDto followDto;
    private FollowGroup mockFollowGroup;

    @BeforeEach
    void setUp() {
        followDto = FollowDto.builder()
            .followingId(1L)
            .followerId(2L)
            .build();

        mockFollowGroup = FollowGroup.create(1L);
    }

    @DisplayName("기존 FollowGroup이 있는 경우 정상 반환 테스트")
    @Test
    void getOrCreateFollowGroup_existingGroup() {
        // Given
        when(followGroupRepository.findByFollowingId(followDto.getFollowingId())).thenReturn(mockFollowGroup);

        // When
        FollowGroup result = followGroupService.getOrCreateFollowGroup(followDto);

        // Then
        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(mockFollowGroup);
        verify(followGroupRepository, times(1)).findByFollowingId(followDto.getFollowingId());
        verify(followGroupRepository, never()).save(any(FollowGroup.class));
    }

    @DisplayName("기존 FollowGroup이 없으면 새로 생성 후 저장 테스트")
    @Test
    void getOrCreateFollowGroup_createNewGroup() {
        // Given
        when(followGroupRepository.findByFollowingId(followDto.getFollowingId())).thenReturn(null);
        when(followGroupRepository.save(any(FollowGroup.class))).thenReturn(mockFollowGroup);

        // When
        FollowGroup result = followGroupService.getOrCreateFollowGroup(followDto);

        // Then
        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(mockFollowGroup);
        verify(followGroupRepository, times(1)).findByFollowingId(followDto.getFollowingId());
        verify(followGroupRepository, times(1)).save(any(FollowGroup.class));
    }


}