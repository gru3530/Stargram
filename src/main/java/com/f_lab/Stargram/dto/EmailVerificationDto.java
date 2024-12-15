package com.f_lab.Stargram.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmailVerificationDto {
    private String email;
    private String code; // 인증 코드 확인 시 사용
}