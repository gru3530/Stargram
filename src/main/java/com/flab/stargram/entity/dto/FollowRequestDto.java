package com.flab.stargram.entity.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.Getter;

@Getter
public class FollowRequestDto {
    @NotNull(message = "팔로우할 사용자 ID는 필수 입니다.")
    @Positive(message = "사용자 ID는 양수로 입력해야 합니다.")
    private Long followingId;
}
