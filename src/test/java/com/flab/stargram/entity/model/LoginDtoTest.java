package com.flab.stargram.entity.model;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.flab.stargram.entity.common.ApiResponseEnum;
import com.flab.stargram.entity.common.ValidationResult;

class LoginDtoTest {

    @Test
    void validateDtoEmpty_error() {
        // Given
        LoginDto dto = new LoginDto();
        dto.setUserName("testUser");
        dto.setPassword("");

        // When
        ValidationResult result = dto.validateEmpty();

        // Then
        assertThat(result.isValid()).isFalse();
        assertThat(result.getError()).isEqualTo(ApiResponseEnum.EMPTY_PASSWORD);
    }

    @Test
    void validateDtoEmpty_success() {
        // Given
        LoginDto dto = new LoginDto();
        dto.setUserName("testUser");
        dto.setPassword("123123");

        // When
        ValidationResult result = dto.validateEmpty();

        // Then
        assertThat(result.isValid()).isTrue();
    }
}