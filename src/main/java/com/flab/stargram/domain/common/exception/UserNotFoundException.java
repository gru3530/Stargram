package com.flab.stargram.domain.common.exception;

import com.flab.stargram.domain.user.model.ApiResponseEnum;

public class UserNotFoundException extends BaseApiException {
    public UserNotFoundException(ApiResponseEnum apiResponseEnum) {
        super(apiResponseEnum);
    }
}
