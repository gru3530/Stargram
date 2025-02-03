package com.flab.stargram.entity.common;

import com.flab.stargram.config.exception.InvalidInputException;

public abstract class BaseDto {
    protected <T> boolean isFieldEmpty(T field) {
        if (field == null) {
            return true;
        }
        if (field instanceof String) {
            return ((String) field).trim().isEmpty();
        }
        return false;
    }

    public static Long parseToLong(String value) {
        try {
            return Long.parseLong(value);
        } catch (NumberFormatException e) {
            throw new InvalidInputException(ApiResponseEnum.INVALID_INPUT);
        }
    }

    protected ValidationResult validationResult = new ValidationResult();

    protected abstract ValidationResult validateEmpty();
}
