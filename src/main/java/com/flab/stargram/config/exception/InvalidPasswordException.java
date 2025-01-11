package com.flab.stargram.config.exception;

import com.flab.stargram.entity.common.ApiResponseEnum;

public class InvalidPasswordException extends BaseApiException {
    public InvalidPasswordException(ApiResponseEnum responseEnum) {
        super(responseEnum);
    }
}
