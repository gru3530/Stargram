package com.flab.stargram.entity.model;

import com.flab.stargram.entity.common.BaseDto;

import lombok.Getter;

@Getter
public class CommentRequestDto extends BaseDto {
    private Long userId;
    private String comment;
    private Long parentCommentId;

    public Boolean isUserIdEmpty() {
        return isFieldEmpty(userId);
    }

    public Boolean isCommentEmpty() {
        return isFieldEmpty(comment);
    }
}
