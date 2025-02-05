package com.flab.stargram.entity.dto;

import com.flab.stargram.entity.common.ApiResponseEnum;
import com.flab.stargram.entity.common.BaseDto;
import com.flab.stargram.entity.common.ValidationResult;

import lombok.Getter;

@Getter
public class CommentRequestDto extends BaseDto {
    private Long userId;
    private String comment;
    private Long parentCommentId;

    @Override
    public ValidationResult validateEmpty() {
        if (isFieldEmpty(userId)) {
            validationResult.addError(ApiResponseEnum.EMPTY_USERID);
        }

        if (isFieldEmpty(comment)) {
            validationResult.addError(ApiResponseEnum.EMPTY_CONTENT);
        }

        return validationResult;
    }
}
