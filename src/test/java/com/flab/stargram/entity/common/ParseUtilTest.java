package com.flab.stargram.entity.common;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.flab.stargram.config.exception.InvalidInputException;

class ParseUtilTest {
    @Test
    void parseLong() {
        String input = "1234";
        Long expectLong = 1234L;

        assertEquals(expectLong, ParseUtil.parseToLong(input));
    }

    @Test
    void parseString() {
        String input = "abc";

        assertThrows(InvalidInputException.class, () -> {
            ParseUtil.parseToLong(input);
        });

    }

    @Test
    void parseStringWithLong() {
        String input = "a2b1c4";

        assertThrows(InvalidInputException.class, () -> {
            ParseUtil.parseToLong(input);
        });
    }
}