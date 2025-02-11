package com.flab.stargram.entity.dto;

import static org.assertj.core.api.Assertions.*;

import java.util.Set;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import jakarta.validation.ConstraintViolation;

class SignUpRequestDtoTest extends ValidatorTest {

    @DisplayName("빈 데이터 입력 시 에러반환 테스트")
    @Test
    void validateDtoEmpty_error() {
        // Given
        SignUpRequestDto dto = SignUpRequestDto.builder()
            .userName(null)
            .password("")
            .build();

        // When
        Set<ConstraintViolation<SignUpRequestDto>> violations = validator.validate(dto);

        // Then
        assertThat(violations).isNotEmpty();
        assertThat(violations).hasSize(3);
        assertThat(violations.stream().map(ConstraintViolation::getMessage))
            .contains("userName은 비어 있을 수 없습니다.", "email은 비어 있을 수 없습니다.", "password는 비어 있을 수 없습니다.");
    }

    @DisplayName("정상 입력 케이스")
    @Test
    void validateDtoEmpty_success() {
        // Given
        SignUpRequestDto dto = SignUpRequestDto.builder()
            .userName("testUser")
            .email("test@f-lab.com")
            .password("123123")
            .build();

        // When
        Set<ConstraintViolation<SignUpRequestDto>> violations = validator.validate(dto);

        // Then
        assertThat(violations).isEmpty();
    }

    @DisplayName("잘못된 email 형식 에러 반환")
    @Test
    void validateDtoEmail_failure() {
        // Given
        SignUpRequestDto dto = SignUpRequestDto.builder()
            .userName("testUser")
            .email("josh.com")
            .password("123123")
            .build();

        // when
        Set<ConstraintViolation<SignUpRequestDto>> violations = validator.validate(dto);

        // then
        assertThat(violations).isNotEmpty();
        assertThat(violations).hasSize(1);
        assertThat(violations.stream().map(ConstraintViolation::getMessage)).contains("올바른 형식의 email을 입력해주세요.");
    }
}