package com.f_lab.Stargram.common.exception;
import com.f_lab.Stargram.common.config.ResponseEnum;

public class InvalidUsernameTokenException extends BaseException {
    public InvalidUsernameTokenException() {
        super(ResponseEnum.INVALID_USER_INPUT);
    }
}
