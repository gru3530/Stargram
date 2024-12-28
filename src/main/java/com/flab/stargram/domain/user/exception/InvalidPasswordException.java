package com.flab.stargram.domain.user.exception;

import com.flab.stargram.domain.user.model.ApiResponseEnum;

public class InvalidPasswordException extends BaseApiException {
    public InvalidPasswordException(ApiResponseEnum responseEnum) {
        super(responseEnum);
    }
}
