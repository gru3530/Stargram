package com.flab.stargram.config.exception;

import java.util.Map;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.flab.stargram.entity.common.ApiResponseEnum;
import com.flab.stargram.entity.common.ApiResult;

import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

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

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiResult> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        Throwable cause = ex.getCause();

        if (cause instanceof InvalidFormatException invalidFormatException) {
            String fieldName = invalidFormatException.getPath().get(0).getFieldName();
            String invalidValue = invalidFormatException.getValue().toString();
            return ApiResult.error(
                ApiResponseEnum.INVALID_INPUT,
                ApiResponseEnum.INVALID_INPUT.getMessage(),
                Map.of(fieldName, invalidValue)
            );
        }

        return ApiResult.error(ApiResponseEnum.INVALID_INPUT, ApiResponseEnum.INVALID_INPUT.getMessage());
    }
}