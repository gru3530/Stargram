package com.flab.stargram.entity.common;

import com.flab.stargram.config.exception.InvalidInputException;

public class ParseUtil {
    private static final String parseLongRegex = "^[0-9]+$";

    public static Long parseToLong(String value) {
        assert value != null : "Input value is null";

        if (value.matches(parseLongRegex)) {
            return Long.parseLong(value);
        } else {
            throw new InvalidInputException(ApiResponseEnum.INVALID_INPUT);
        }
    }
}
