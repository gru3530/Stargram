package com.flab.stargram.entity.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class SignUpRequestDto {
    @Size(min = 2, max = 20, message = "userName은 2~20자로 입력해야 합니다.")
    @NotBlank(message = "userName은 비어 있을 수 없습니다.")
    private String userName;

    @Size(min = 8, max = 50, message = "비밀번호는 8~50자로 입력해야 합니다.")
    @NotBlank(message = "password는 비어 있을 수 없습니다.")
    private String password;

    @Email(message = "올바른 형식의 email을 입력해주세요.")
    @NotBlank(message = "email은 비어 있을 수 없습니다.")
    private String email;
}
