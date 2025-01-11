package com.flab.stargram.domain.common.exception;

import com.flab.stargram.domain.common.model.ApiResponseEnum;

public class InvalidInputException extends BaseApiException {
    public InvalidInputException(ApiResponseEnum responseEnum) {
        super(responseEnum);
    }
}
