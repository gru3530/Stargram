package com.flab.stargram.entity.dto;

import com.flab.stargram.entity.common.ApiResponseEnum;
import com.flab.stargram.entity.common.BaseDto;
import com.flab.stargram.entity.common.ValidationResult;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class PostRequestDto extends BaseDto {
    @NotNull(message = "userID는 필수 입니다.")
    private Long userId;

    @NotEmpty(message = "content는 필수 입니다.")
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
