package com.flab.stargram.entity.dto;

import static org.assertj.core.api.Assertions.*;

import java.util.Set;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.flab.stargram.entity.common.ApiResponseEnum;
import com.flab.stargram.entity.common.ValidationResult;

import jakarta.validation.ConstraintViolation;

class SignUpRequestDtoTest extends ValidatorTest{

    @DisplayName("빈 데이터 입력 시 에러반환 테스트")
    @Test
    void validateDtoEmpty_error() {
        // Given
        SignUpRequestDto dto = SignUpRequestDto.builder()
            .userName(null)
            .password("")
            .build();

        // When
        ValidationResult result = dto.validateEmpty();

        // Then
        assertThat(result.isValid()).isFalse();
        assertThat(result.getError()).isEqualTo(ApiResponseEnum.EMPTY_USERNAME);
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
        ValidationResult result = dto.validateEmpty();

        // Then
        assertThat(result.isValid()).isTrue();
    }

    @DisplayName("잘못된 email 형식 에러 반환")
    @Test
    void validateDtoEmail_failure(){
        // Given
        SignUpRequestDto dto = SignUpRequestDto.builder()
            .userName("testUser")
            .email("test.com")
            .password("123123")
            .build();

        // when
        Set<ConstraintViolation<SignUpRequestDto>> violations = validator.validate(dto);

        // then
        assertThat(violations).isNotEmpty();
        assertThat(violations.iterator().next().getMessage()).isEqualTo("올바른 형식의 email을 입력해주세요");
    }
}