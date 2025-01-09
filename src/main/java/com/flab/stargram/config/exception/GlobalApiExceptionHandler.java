package com.flab.stargram.config.exception;

import com.flab.stargram.entity.common.ApiResponseEnum;
import com.flab.stargram.entity.common.ApiResult;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalApiExceptionHandler {

    @ExceptionHandler(BaseApiException.class)
    public ResponseEntity<ApiResult> handleBaseApiException(BaseApiException e) {
        return ApiResult.error(e.getResponseEnum(), e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResult> handleGeneralException(Exception e) {
        return ApiResult.error(ApiResponseEnum.FAILURE, e.getMessage());
    }
}