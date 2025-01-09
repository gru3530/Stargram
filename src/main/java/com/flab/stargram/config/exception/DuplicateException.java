package com.flab.stargram.config.exception;

import com.flab.stargram.entity.common.ApiResponseEnum;

public class DuplicateException extends BaseApiException {
    public DuplicateException(ApiResponseEnum responseEnum) {
        super(responseEnum);
    }
}
