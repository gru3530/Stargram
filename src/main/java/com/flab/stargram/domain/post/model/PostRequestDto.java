package com.flab.stargram.domain.post.model;

import com.flab.stargram.domain.common.model.BaseDto;

import lombok.Getter;

@Getter
public class PostRequestDto extends BaseDto {
    private Long userId;
    private String content;

    public boolean isUserIdEmpty() {
        return isFieldEmpty(userId);
    }

    public boolean isContentEmpty() {
        return isFieldEmpty(content);
    }
}
