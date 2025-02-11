package com.flab.stargram.entity.dto;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.flab.stargram.entity.common.ApiResponseEnum;
import com.flab.stargram.entity.common.ValidationResult;

class SignUpRequestDtoTest {

    @Test
    void validateDtoEmpty_error() {
        // Given
        SignUpRequestDto dto = new SignUpRequestDto();
        dto.setUserName(null);
        dto.setPassword("");

        // When
        ValidationResult result = dto.validateEmpty();

        // Then
        assertThat(result.isValid()).isFalse();
        assertThat(result.getError()).isEqualTo(ApiResponseEnum.EMPTY_USERNAME);
    }

    @Test
    void validateDtoEmpty_success() {
        // Given
        SignUpRequestDto dto = new SignUpRequestDto();
        dto.setUserName("testUser");
        dto.setEmail("test@f-lab.com");
        dto.setPassword("123123");

        // When
        ValidationResult result = dto.validateEmpty();

        // Then
        assertThat(result.isValid()).isTrue();
    }
}