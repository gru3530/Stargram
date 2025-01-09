package com.flab.stargram.domain.user.exception;

import com.flab.stargram.domain.user.model.ApiResponseEnum;

public class EmptyInputException extends BaseApiException {
    public EmptyInputException(ApiResponseEnum responseEnum) {
        super(responseEnum);
    }
}
