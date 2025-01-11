package com.flab.stargram.entity.common;


import com.flab.stargram.config.exception.InvalidInputException;

import lombok.Getter;

@Getter
public class ValidationResult {
    private ApiResponseEnum error;

    public void addError(ApiResponseEnum error) {
        if (this.error == null) { // 첫 번째 오류만 기록
            this.error = error;
        }
    }

    public boolean isValid() {
        return error == null;
    }

    public void ifInvalidThrow() {
        if (!isValid()) {
            throw new InvalidInputException(error);
        }
    }
}