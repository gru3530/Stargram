package com.flab.stargram.entity.dto;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.flab.stargram.entity.common.ApiResponseEnum;
import com.flab.stargram.entity.common.ValidationResult;

class LoginDtoTest {
    @DisplayName("userName에 빈 데이터 입력한 경우 에러 반환")
    @Test
    void validateDtoEmpty_errorEmptyuserName() {
        // Given
        LoginDto dto = LoginDto.builder()
            .userName("")
            .password("1234")
            .build();

        // When
        ValidationResult result = dto.validateEmpty();

        // Then
        assertThat(result.isValid()).isFalse();
        assertThat(result.getError()).isEqualTo(ApiResponseEnum.EMPTY_USERNAME);
    }

    @DisplayName("userName에 null 입력한 경우 에러 반환")
    @Test
    void validateDtoEmpty_errorNulluserName() {
        // Given
        LoginDto dto = LoginDto.builder()
            .userName(null)
            .password("1234")
            .build();

        // When
        ValidationResult result = dto.validateEmpty();

        // Then
        assertThat(result.isValid()).isFalse();
        assertThat(result.getError()).isEqualTo(ApiResponseEnum.EMPTY_USERNAME);
    }


    @DisplayName("비밀번호 비어있는 데이터 입력한 경우 에러 반환")
    @Test
    void validateDtoEmpty_error() {
        // Given
        LoginDto dto = LoginDto.builder()
            .userName("testUser")
            .password("")
            .build();

        // When
        ValidationResult result = dto.validateEmpty();

        // Then
        assertThat(result.isValid()).isFalse();
        assertThat(result.getError()).isEqualTo(ApiResponseEnum.EMPTY_PASSWORD);
    }

    @DisplayName("정상 케이스")
    @Test
    void validateDtoEmpty_success() {
        // Given
        LoginDto dto = LoginDto.builder()
            .userName("testUser")
            .password("123123")
            .build();

        // When
        ValidationResult result = dto.validateEmpty();

        // Then
        assertThat(result.isValid()).isTrue();
    }
}