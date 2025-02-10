package com.flab.stargram.entity.common;

import java.util.regex.Pattern;

import com.flab.stargram.config.exception.InvalidInputException;

public class ParseUtil {
    private static final Pattern parseLongPattern = Pattern.compile("^[0-9]+$");

    public static Long parseToLong(String value) {
        assert value != null : "Input value is null";

        if (parseLongPattern.matcher(value).matches()) {
            return Long.parseLong(value);
        } else {
            throw new InvalidInputException(ApiResponseEnum.INVALID_INPUT);
        }
    }
}
