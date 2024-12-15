package com.f_lab.Stargram.service;

import com.f_lab.Stargram.domain.user.UserInfo;

public interface UserService {
    UserInfo registerStep1(String userName, String password, String email);
    UserInfo registerStep2(Long userId, String interests);
    boolean authenticateUser(String userName, String password);
    void sendEmailVerificationCode(String email);
    boolean verifyEmail(String email, String code);
}
