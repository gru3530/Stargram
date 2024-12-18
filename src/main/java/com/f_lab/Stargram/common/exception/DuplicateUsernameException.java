package com.f_lab.Stargram.common.exception;
import com.f_lab.Stargram.common.config.ResponseEnum;

public class DuplicateUsernameException extends BaseException {
    public DuplicateUsernameException() {
        super(ResponseEnum.DUPLICATE_USERNAME);
    }
}
