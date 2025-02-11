package com.flab.stargram.entity.dto;

import static org.assertj.core.api.Assertions.*;

import java.util.Set;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import jakarta.validation.ConstraintViolation;

class FollowDtoTest extends ValidatorTest {

    @DisplayName("정상 케이스")
    @Test
    public void testValidFollowDto() {
        FollowDto dto = FollowDto.builder()
            .followingId(1L)
            .followerId(2L)
            .build();

        Set<ConstraintViolation<FollowDto>> violations = validator.validate(dto);
        assertThat(violations).isEmpty();
    }

    @DisplayName("followingID가 null인 경우 검증 오류 발생")
    @Test
    public void testInvalidFollowDto_FollowingIdIsNull() {
        FollowDto dto = FollowDto.builder()
            .followingId(null)
            .followerId(2L)
            .build();

        Set<ConstraintViolation<FollowDto>> violations = validator.validate(dto);

        assertThat(violations).isNotEmpty();
        assertThat(violations.iterator().next().getMessage()).isEqualTo("following ID는 필수 입니다.");
    }

    @DisplayName("followingId와 followerId가 모두 null인 경우 두 개의 검증 오류 발생")
    @Test
    void bothFollowingIdAndFollowerIdAreNull() {
        FollowDto dto = FollowDto.builder()
            .followingId(null)
            .followerId(null)
            .build();

        Set<ConstraintViolation<FollowDto>> violations = validator.validate(dto);

        assertThat(violations).hasSize(2);
        assertThat(violations.stream().map(ConstraintViolation::getMessage))
            .contains("following ID는 필수 입니다.", "follower ID는 필수 입니다.");
    }

    @DisplayName("followingId가 음수인 경우 검증 오류 발생")
    @Test
    void followingIdIsNegative() {
        FollowDto dto = FollowDto.builder()
            .followingId(-1L)
            .followerId(2L)
            .build();

        Set<ConstraintViolation<FollowDto>> violations = validator.validate(dto);

        assertThat(violations).isNotEmpty();
        assertThat(violations.iterator().next().getMessage()).isEqualTo("following ID는 양수로 입력해야 합니다.");
    }

    @DisplayName("followerId가 null인 경우 검증 오류 발생")
    @Test
    void followerIdIsNull() {
        FollowDto dto = FollowDto.builder()
            .followingId(1L)
            .followerId(null)
            .build();

        Set<ConstraintViolation<FollowDto>> violations = validator.validate(dto);

        assertThat(violations).isNotEmpty();
        assertThat(violations.iterator().next().getMessage()).isEqualTo("follower ID는 필수 입니다.");
    }

    @DisplayName("followerId가 음수인 경우 검증 오류 발생")
    @Test
    void followerIdIsNegative() {
        FollowDto dto = FollowDto.builder()
            .followingId(1L)
            .followerId(-2L)
            .build();

        Set<ConstraintViolation<FollowDto>> violations = validator.validate(dto);

        assertThat(violations).isNotEmpty();
        assertThat(violations.iterator().next().getMessage()).isEqualTo("follower ID는 양수로 입력해야 합니다.");
    }

}