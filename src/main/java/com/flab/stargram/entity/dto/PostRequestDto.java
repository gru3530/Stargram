package com.flab.stargram.entity.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class PostRequestDto {
    @NotNull(message = "userID는 필수 입니다.")
    private Long userId;

    @NotEmpty(message = "content는 필수 입니다.")
    private String content;
}
