package com.flab.stargram.service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.flab.stargram.config.exception.DuplicateException;
import com.flab.stargram.config.exception.InvalidPasswordException;
import com.flab.stargram.config.exception.DataNotFoundException;
import com.flab.stargram.entity.common.ApiResponseEnum;
import com.flab.stargram.entity.model.LoginDto;
import com.flab.stargram.entity.model.SignUpRequestDto;
import com.flab.stargram.entity.model.User;
import com.flab.stargram.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public User signUp(SignUpRequestDto dto) {
        if (hasUserName(dto)) {
            throw new DuplicateException(ApiResponseEnum.DUPLICATE_USERNAME);
        }

        if (hasEmail(dto)) {
            throw new DuplicateException(ApiResponseEnum.DUPLICATE_EMAIL);
        }

        User user = new User(dto);
        userRepository.save(user);
        return user;
    }

    @Transactional
    public User login(LoginDto dto) {
        Optional<User> byUserName = findByUsername(dto);
        if (byUserName.isEmpty()) {
            throw new DataNotFoundException(ApiResponseEnum.USER_NOT_FOUND);
        }

        User user = byUserName.get();
        if (!user.isCorrectPassword(dto)) {
            throw new InvalidPasswordException(ApiResponseEnum.INVALID_PASSWORD);
        }

        user.recordSuccessfulLogin();
        return user;
    }

    public boolean hasUserName(String userName){
        return userRepository.existsByUserName(userName);
    }

    public boolean hasUserId(Long userId){
        return userRepository.existsById(userId);
    }

    private boolean hasUserName(SignUpRequestDto dto) {
        return userRepository.existsByUserName(dto.getUserName());
    }

    private boolean hasEmail(SignUpRequestDto dto) {
        return userRepository.existsByEmail(dto.getEmail());
    }

    private Optional<User> findByUsername(LoginDto dto) {
        return userRepository.findByUserName(dto.getUserName());
    }
}
