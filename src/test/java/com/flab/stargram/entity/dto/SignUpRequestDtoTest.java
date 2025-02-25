package com.flab.stargram.entity.dto;

import static org.assertj.core.api.Assertions.*;

import java.util.Set;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import jakarta.validation.ConstraintViolation;

class SignUpRequestDtoTest extends ValidatorTest {

    @DisplayName("SignupDto 빈 데이터 입력 시 validate 에러반환")
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
        assertThat(violations).hasSize(4);
        assertThat(violations.stream().map(ConstraintViolation::getMessage))
            .contains("userName은 비어 있을 수 없습니다.", "email은 비어 있을 수 없습니다.", "password는 비어 있을 수 없습니다.", "비밀번호는 8~50자로 입력해야 합니다.");
    }

    @DisplayName("정상 입력 케이스")
    @Test
    void validateDtoEmpty_success() {
        // Given
        SignUpRequestDto dto = SignUpRequestDto.builder()
            .userName("testUser")
            .email("test@f-lab.com")
            .password("12345678")
            .build();

        // When
        Set<ConstraintViolation<SignUpRequestDto>> violations = validator.validate(dto);

        // Then
        assertThat(violations).isEmpty();
    }

    @DisplayName("SignUpDto email 잘못된 형식 입력 시 validate 에러 반환")
    @Test
    void validateDtoEmail_failure() {
        // Given
        SignUpRequestDto dto = SignUpRequestDto.builder()
            .userName("testUser")
            .email("josh.com")
            .password("12345678")
            .build();

        // when
        Set<ConstraintViolation<SignUpRequestDto>> violations = validator.validate(dto);

        // then
        assertThat(violations).isNotEmpty();
        assertThat(violations).hasSize(1);
        assertThat(violations.stream().map(ConstraintViolation::getMessage)).contains("올바른 형식의 email을 입력해주세요.");
    }

    @DisplayName("SignUpdto userName이 2자 미만일 경우 validate 에러 반환")
    @Test
    void validateUserNameTooShort() {
        // Given
        SignUpRequestDto dto = SignUpRequestDto.builder()
            .userName("g")
            .email("gru@f-lab.com")
            .password("12345678")
            .build();

        //when
        Set<ConstraintViolation<SignUpRequestDto>> violations = validator.validate(dto);

        //then
        assertThat(violations).isNotEmpty();
        assertThat(violations.iterator().next().getMessage()).isEqualTo("userName은 2~20자로 입력해야 합니다.");
    }

    @DisplayName("SignUpdto userName이 20자 초과일 경우 validate 에러 반환")
    @Test
    void validateUserNameTooLong() {
        // Given
        SignUpRequestDto dto = SignUpRequestDto.builder()
            .userName("abcdefghijklmnopqrstu")
            .email("gru@f-lab.com")
            .password("12345678")
            .build();

        //when
        Set<ConstraintViolation<SignUpRequestDto>> violations = validator.validate(dto);

        //then
        assertThat(violations).isNotEmpty();
        assertThat(violations.iterator().next().getMessage()).isEqualTo("userName은 2~20자로 입력해야 합니다.");
    }

    @DisplayName("SignUpdto password가 8자 미만일 경우 validate 에러 반환")
    @Test
    void validatePasswordTooShort() {
        // Given
        SignUpRequestDto dto = SignUpRequestDto.builder()
            .userName("gru")
            .email("gru@f-lab.com")
            .password("1234567")
            .build();

        // when
        Set<ConstraintViolation<SignUpRequestDto>> violations = validator.validate(dto);

        // Then
        assertThat(violations).isNotEmpty();
        assertThat(violations.iterator().next().getMessage()).isEqualTo("비밀번호는 8~50자로 입력해야 합니다.");
    }

    @DisplayName("SignUpdto password가 50자 초과일 경우 validate 에러 반환")
    @Test
    void validatePasswordTooLong() {
        // Given
        SignUpRequestDto dto = SignUpRequestDto.builder()
            .userName("gru")
            .email("gru@f-lab.com")
            .password("123456789012345678901234567890123456789012345678901")
            .build();

        // when
        Set<ConstraintViolation<SignUpRequestDto>> violations = validator.validate(dto);

        // Then
        assertThat(violations).isNotEmpty();
        assertThat(violations.iterator().next().getMessage()).isEqualTo("비밀번호는 8~50자로 입력해야 합니다.");
    }
}