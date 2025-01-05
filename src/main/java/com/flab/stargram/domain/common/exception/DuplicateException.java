package com.flab.stargram.domain.common.exception;

import com.flab.stargram.domain.user.model.ApiResponseEnum;

public class DuplicateException extends BaseApiException {
    public DuplicateException(ApiResponseEnum responseEnum) {
        super(responseEnum);
    }
}
