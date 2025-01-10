package com.flab.stargram.domain.common.exception;

import com.flab.stargram.domain.common.model.ApiResponseEnum;

public class InvalidPasswordException extends BaseApiException {
    public InvalidPasswordException(ApiResponseEnum responseEnum) {
        super(responseEnum);
    }
}
