package com.flab.stargram.entity.dto;

import com.flab.stargram.entity.common.ApiResponseEnum;
import com.flab.stargram.entity.common.BaseDto;
import com.flab.stargram.entity.common.ValidationResult;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginDto extends BaseDto {
    private String userName;
    private String password;

    @Override
    public ValidationResult validateEmpty() {
        if (isFieldEmpty(userName)) {
            validationResult.addError(ApiResponseEnum.EMPTY_USERNAME);
        }

        if (isFieldEmpty(password)) {
	        validationResult.addError(ApiResponseEnum.EMPTY_PASSWORD);
        }

		return validationResult;
    }
}
