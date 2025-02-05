package com.flab.stargram.entity.dto;

import com.flab.stargram.entity.common.ApiResponseEnum;
import com.flab.stargram.entity.common.BaseDto;
import com.flab.stargram.entity.common.ValidationResult;

import lombok.Getter;

@Getter
public class PostRequestDto extends BaseDto {
    private Long userId;
    private String content;

    @Override
    public ValidationResult validateEmpty() {
        if (isFieldEmpty(userId)) {
            validationResult.addError(ApiResponseEnum.EMPTY_USERID);
        }

        if (isFieldEmpty(content)) {
            validationResult.addError(ApiResponseEnum.EMPTY_CONTENT);
        }

        return validationResult;
    }
}
