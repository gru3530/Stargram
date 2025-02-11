package com.flab.stargram.entity.dto;

import com.flab.stargram.entity.common.ApiResponseEnum;
import com.flab.stargram.entity.common.BaseDto;
import com.flab.stargram.entity.common.ValidationResult;

import lombok.Getter;

@Getter
public class FollowRequestDto extends BaseDto {
    private Long followingId;

    @Override
    public ValidationResult validateEmpty() {
        if (isFieldEmpty(followingId)) {
            validationResult.addError(ApiResponseEnum.EMPTY_FOLLOWING_ID);
        }

        return validationResult;
    }
}
