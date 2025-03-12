package com.flab.stargram.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.flab.stargram.config.exception.DuplicateException;
import com.flab.stargram.config.exception.InvalidPasswordException;
import com.flab.stargram.config.exception.DataNotFoundException;
import com.flab.stargram.entity.common.ApiResponseEnum;
import com.flab.stargram.entity.common.UserTypeEnum;
import com.flab.stargram.entity.dto.LoginDto;
import com.flab.stargram.entity.dto.PostRequestDto;
import com.flab.stargram.entity.dto.SignUpRequestDto;
import com.flab.stargram.entity.model.Post;
import com.flab.stargram.entity.model.User;
import com.flab.stargram.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public User signUp(SignUpRequestDto dto) {
        checkUserDuplication(dto);

        return userRepository.save(User.createUserOf(dto));
    }

    @Transactional
    public User login(LoginDto dto) {
        User user = fetchUser(dto);

        if (!user.isCorrectPassword(dto)) {
            throw new InvalidPasswordException(ApiResponseEnum.INVALID_PASSWORD);
        }

        user.recordSuccessfulLogin();
        return user;
    }

    private void checkUserDuplication(SignUpRequestDto dto) {
        User existingUser = fetchExistingUser(dto);

        if (existingUser != null) {
            checkDuplicates(dto, existingUser);
        }
    }

    private void checkDuplicates(SignUpRequestDto dto, User existingUser) {
        if (existingUser.getUserName().equals(dto.getUserName())) {
            throw new DuplicateException(ApiResponseEnum.DUPLICATE_USERNAME);
        }

        if (existingUser.getEmail().equals(dto.getEmail())) {
            throw new DuplicateException(ApiResponseEnum.DUPLICATE_EMAIL);
        }
    }

    private User fetchExistingUser(SignUpRequestDto dto) {
        return userRepository.findByUserNameOrEmail(dto.getUserName(), dto.getEmail());
    }

    private User fetchUser(LoginDto dto) {
        User user = userRepository.findByUserName(dto.getUserName());
        if (user == null) {
            throw new DataNotFoundException(ApiResponseEnum.USER_NOT_FOUND);
        }

        return user;
    }

    public boolean hasUserId(Long userId) {
        return userRepository.existsById(userId);
    }

    public UserTypeEnum getUserType(Long userId) {
        User user = userRepository.findByUserId(userId);
        if (user == null) {
            throw new DataNotFoundException(ApiResponseEnum.USER_NOT_FOUND);
        }

        return user.getUserType();
    }
}
