package com.f_lab.Stargram.common.exception;
import com.f_lab.Stargram.common.config.ResponseEnum;

public class DuplicateEmailException extends BaseException {
    public DuplicateEmailException() {
        super(ResponseEnum.DUPLICATE_REQUEST);
    }
}
