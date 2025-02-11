package com.flab.stargram.entity.dto;

import com.flab.stargram.entity.common.ApiResponseEnum;
import com.flab.stargram.entity.common.BaseDto;
import com.flab.stargram.entity.common.ValidationResult;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
public class SignUpRequestDto {
    @NotBlank(message = "userName은 비어 있을 수 없습니다.")
    private String userName;

    @NotBlank(message = "password는 비어 있을 수 없습니다.")
    private String password;

    @Email(message = "올바른 형식의 email을 입력해주세요.")
    @NotBlank(message = "email은 비어 있을 수 없습니다.")
    private String email;
}
