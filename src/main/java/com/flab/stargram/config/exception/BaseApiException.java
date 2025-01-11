package com.flab.stargram.config.exception;

import com.flab.stargram.entity.common.ApiResponseEnum;

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