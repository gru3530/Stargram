package com.flab.stargram.entity.common;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.flab.stargram.config.exception.InvalidInputException;

class ParseUtilTest {
    @Test
    void parseLong() {
        String input = "1234";
        Long expectLong = 1234L;

        assertEquals(expectLong, ParseUtil.parseToLong(input));
    }

    @DisplayName("문자열이 들어왔을경우 에러 반환 테스트")
    @Test
    void parseString() {
        String input = "abc";

        assertThrows(InvalidInputException.class, () -> {
            ParseUtil.parseToLong(input);
        });

    }

    @DisplayName("숫자와 L이 함께 들어왔을경우 에러 반환 테스트")
    @Test
    void parseLongString() {
        String input = "365L";

        assertThrows(InvalidInputException.class, () -> {
            ParseUtil.parseToLong(input);
        });

    }


    @DisplayName("문자열과 숫자가 섞여 들어왔을경우 에러 반환 테스트")
    @Test
    void parseStringWithLong() {
        String input = "a2b1c4";

        assertThrows(InvalidInputException.class, () -> {
            ParseUtil.parseToLong(input);
        });
    }

    @DisplayName("빈 문자열이 들어왔을경우 에러 반환 테스트")
    @Test
    void parseEmptyString() {
        String input = "";

        assertThrows(InvalidInputException.class, () -> {
            ParseUtil.parseToLong(input);
        });
    }
}