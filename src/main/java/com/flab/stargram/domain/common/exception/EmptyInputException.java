package com.flab.stargram.domain.common.exception;

import com.flab.stargram.domain.common.model.ApiResponseEnum;

public class EmptyInputException extends BaseApiException {
    public EmptyInputException(ApiResponseEnum responseEnum) {
        super(responseEnum);
    }
}
