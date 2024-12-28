package com.flab.stargram.domain.user.exception;

import com.flab.stargram.domain.user.model.ApiResponseEnum;
import com.flab.stargram.domain.user.model.ApiResult;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(basePackages = "com.flab.stargram.domain.user")
public class UserGlobalExceptionHandler {

    @ExceptionHandler(BaseApiException.class)
    public ApiResult<?> handleBaseApiException(BaseApiException e) {
        return ApiResult.error(e.getResponseEnum(), e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ApiResult<?> handleGeneralException(Exception e) {
        return ApiResult.error(ApiResponseEnum.FAILURE, e.getMessage());
    }
}