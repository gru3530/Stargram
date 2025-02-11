package com.flab.stargram.entity.dto;

import com.flab.stargram.entity.common.ApiResponseEnum;
import com.flab.stargram.entity.common.BaseDto;
import com.flab.stargram.entity.common.ValidationResult;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignUpRequestDto extends BaseDto {
    private String userName;
    private String email;
    private String password;

    @Override
    public ValidationResult validateEmpty() {
        if (isFieldEmpty(userName)) {
            validationResult.addError(ApiResponseEnum.EMPTY_USERNAME);
        }

        if (isFieldEmpty(email)) {
            validationResult.addError(ApiResponseEnum.EMPTY_EMAIL);
        }

        if (isFieldEmpty(password)) {
            validationResult.addError(ApiResponseEnum.EMPTY_PASSWORD);
        }

        return validationResult;
    }
}
