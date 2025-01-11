package com.flab.stargram.domain.common.exception;

import com.flab.stargram.domain.common.model.ApiResponseEnum;

public class DataNotFoundException extends BaseApiException {
    public DataNotFoundException(ApiResponseEnum apiResponseEnum) {
        super(apiResponseEnum);
    }
}
