package com.flab.stargram.entity.common;

import com.flab.stargram.config.exception.InvalidInputException;

public class ParseUtil {
    public static Long parseToLong(String value) {
        if (value.isEmpty()) {
            throw new InvalidInputException(ApiResponseEnum.INVALID_INPUT);
        }

        try {
            return Long.parseLong(value);
        } catch (NumberFormatException e) {
            throw new InvalidInputException(ApiResponseEnum.INVALID_INPUT);
        }
    }
}
