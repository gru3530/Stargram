package com.f_lab.Stargram.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterRequestDto {
    private String userName;
    private String email;
    private String password;
}
