package com.f_lab.Stargram.common.exception;

import com.f_lab.Stargram.common.config.ResponseEnum;

public class InvalidInputException extends BaseException {
    public InvalidInputException(String message) {
        super(ResponseEnum.INVALID_USER_INPUT);;
    }
}
