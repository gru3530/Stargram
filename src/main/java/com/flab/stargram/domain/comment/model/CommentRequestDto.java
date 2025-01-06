package com.flab.stargram.domain.comment.model;

import com.flab.stargram.domain.common.model.BaseDto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class CommentRequestDto extends BaseDto {
    private Long postId;
    private String comment;
    private Long parentCommentId;

    public Boolean isPostIdEmpty() {
        return isFieldEmpty(postId);
    }

    public Boolean isCommentEmpty() {
        return isFieldEmpty(comment);
    }
}
