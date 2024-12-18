package com.f_lab.Stargram.domain.user.service;

import com.f_lab.Stargram.domain.user.User;
import com.f_lab.Stargram.domain.user.mapper.UserRepository;
import com.f_lab.Stargram.model.LoginRequestDto;
import com.f_lab.Stargram.model.RegisterRequestDto;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public String loginUser(LoginRequestDto dto) {
        User user = userRepository.findByUsername(dto.getUserName())
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));
        if (!passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 틀렸습니다.");
        }
        return "로그인 성공!";
    }

    @Override
    public String registerUser(RegisterRequestDto dto) {
        if (userRepository.findByUsername(dto.getUserName()).isPresent()) {
            throw new IllegalArgumentException("이미 존재하는 사용자 이름입니다.");
        }

        User user = new User();
        user.setUsername(dto.getUserName());
        user.setEmail(dto.getEmail());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));

        userRepository.save(user);
        return "회원가입 성공!";
    }
}