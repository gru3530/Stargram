package com.flab.stargram.entity.model;

import com.flab.stargram.entity.common.BaseDto;

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
