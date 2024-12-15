package com.f_lab.Stargram.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterRequestDto {
    private String userName;
    private String password;
    private String phoneNumber;
    private String email;
}