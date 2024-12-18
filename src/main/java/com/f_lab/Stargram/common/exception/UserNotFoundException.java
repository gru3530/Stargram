package com.f_lab.Stargram.common.exception;
import com.f_lab.Stargram.common.config.ResponseEnum;

public class UserNotFoundException extends BaseException  {
    public UserNotFoundException(String message) {
        super(ResponseEnum.NON_EXIST_USER);
    }
}
