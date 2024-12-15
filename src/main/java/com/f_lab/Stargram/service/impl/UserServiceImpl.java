package com.f_lab.Stargram.service.impl;

import com.f_lab.Stargram.domain.user.UserInfo;
import com.f_lab.Stargram.repository.UserRepository;
import com.f_lab.Stargram.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private final Map<String, String> emailVerificationStore = new HashMap<>();

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserInfo registerStep1(String userName, String password, String email) {
        if (userRepository.existsByEmail(email)) {
            throw new IllegalArgumentException("이미 존재하는 이메일입니다.");
        }
        if (userRepository.existsByUserName(userName)) {
            throw new IllegalArgumentException("이미 존재하는 사용자 이름입니다.");
        }

        String encodedPassword = passwordEncoder.encode(password);

        UserInfo user = new UserInfo();
        user.setUserName(userName);
        user.setPassword(encodedPassword);
        user.setEmail(email);

        return userRepository.save(user);
    }

    @Override
    public UserInfo registerStep2(Long userId, String interests) {
        UserInfo user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));

        user.setInterests(interests);
        return userRepository.save(user);
    }

    @Override
    public boolean authenticateUser(String userName, String password) {
        UserInfo user = userRepository.findByUserName(userName)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));

        return passwordEncoder.matches(password, user.getPassword());
    }

    @Override
    public void sendEmailVerificationCode(String email) {
        String code = generateVerificationCode(); // 랜덤 코드 생성
        emailVerificationStore.put(email, code);
        System.out.println("인증 코드 전송: " + code); // 실제로는 이메일 전송 로직
    }

    @Override
    public boolean verifyEmail(String email, String code) {
        String storedCode = emailVerificationStore.get(email);
        if (storedCode != null && storedCode.equals(code)) {
            Optional<UserInfo> userOptional = userRepository.findByEmail(email);
            userOptional.ifPresent(user -> {
                user.setEmailVerified(true);
                userRepository.save(user);
            });
            return true;
        }
        return false;
    }

    private String generateVerificationCode() {
        return String.valueOf((int) (Math.random() * 900000) + 100000); // 6자리 랜덤 숫자
    }
}
