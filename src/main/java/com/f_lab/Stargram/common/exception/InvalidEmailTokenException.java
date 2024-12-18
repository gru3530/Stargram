package com.f_lab.Stargram.common.exception;
import com.f_lab.Stargram.common.config.ResponseEnum;

public class InvalidEmailTokenException extends BaseException {
    public InvalidEmailTokenException() {
        super(ResponseEnum.INVALID_USER_INPUT);
    }
}