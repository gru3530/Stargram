package com.flab.stargram.domain.common.exception;

import com.flab.stargram.domain.common.model.ApiResponseEnum;

public class BaseApiException extends RuntimeException {
    private final ApiResponseEnum responseEnum;

    public BaseApiException(ApiResponseEnum responseEnum) {
        super(responseEnum.getMessage());
        this.responseEnum = responseEnum;
    }

    public ApiResponseEnum getResponseEnum() {
        return responseEnum;
    }
}