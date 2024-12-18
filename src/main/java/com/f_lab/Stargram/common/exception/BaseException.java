package com.f_lab.Stargram.common.exception;

import lombok.Getter;
import com.f_lab.Stargram.common.config.ResponseEnum;

@Getter
public class BaseException extends RuntimeException {
    private final ResponseEnum responseEnum;

    public BaseException(ResponseEnum responseEnum) {
        super(responseEnum.getMessage());
        this.responseEnum = responseEnum;
    }
}