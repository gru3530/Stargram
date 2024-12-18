package com.f_lab.Stargram.domain.user.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SignUpRequestDto {
    private String email;
    private String username;
    private String password;
    private String emailToken;
    private String usernameToken;
}
