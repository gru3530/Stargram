package com.f_lab.Stargram.domain.user.service;

import com.f_lab.Stargram.domain.user.User;

import com.f_lab.Stargram.model.LoginRequestDto;
import com.f_lab.Stargram.model.RegisterRequestDto;

public interface UserService {
    String loginUser(LoginRequestDto dto);
    String registerUser(RegisterRequestDto dto);
}