package com.flab.stargram.config.exception;

import com.flab.stargram.entity.common.ApiResponseEnum;

public class InvalidInputException extends BaseApiException {
    public InvalidInputException(ApiResponseEnum responseEnum) {
        super(responseEnum);
    }
}
