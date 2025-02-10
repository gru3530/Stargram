package com.flab.stargram.entity.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class FollowDto {
    @NotNull(message = "following ID는 필수 입니다.")
    @Positive(message = "following ID는 양수로 입력해야 합니다.")
    private Long followingId;

    @NotNull(message = "follower ID는 필수 입니다.")
    @Positive(message = "follower ID는 양수로 입력해야 합니다.")
    private Long followerId;
}
