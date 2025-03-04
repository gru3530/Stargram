package com.flab.stargram.entity.dto;

import static org.assertj.core.api.Assertions.*;

import java.util.Set;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import jakarta.validation.ConstraintViolation;

class LoginDtoTest extends ValidatorTest {
    @DisplayName("userName에 빈 데이터 입력한 경우 에러 반환")
    @Test
    void validateDtoEmpty_errorEmptyuserName() {
        // Given
        LoginDto dto = LoginDto.builder()
            .userName("")
            .password("1234")
            .build();

        // When
        Set<ConstraintViolation<LoginDto>> violations = validator.validate(dto);

        // Then
        assertThat(violations).isNotEmpty();
        assertThat(violations.iterator().next().getMessage()).isEqualTo("userName은 비어 있을 수 없습니다.");
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
        Set<ConstraintViolation<LoginDto>> violations = validator.validate(dto);

        // Then
        assertThat(violations).isNotEmpty();
        assertThat(violations.iterator().next().getMessage()).isEqualTo("userName은 비어 있을 수 없습니다.");
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
        Set<ConstraintViolation<LoginDto>> violations = validator.validate(dto);

        // Then
        assertThat(violations).isNotEmpty();
        assertThat(violations.iterator().next().getMessage()).isEqualTo("password는 비어 있을 수 없습니다.");
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
        Set<ConstraintViolation<LoginDto>> violations = validator.validate(dto);

        // Then
        assertThat(violations).isEmpty();
    }
}