package com.flab.stargram.config.exception;

import com.flab.stargram.entity.common.ApiResponseEnum;

public class DataNotFoundException extends BaseApiException {
    public DataNotFoundException(ApiResponseEnum apiResponseEnum) {
        super(apiResponseEnum);
    }
}
