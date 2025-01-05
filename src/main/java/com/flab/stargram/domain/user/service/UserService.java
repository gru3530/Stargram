package com.flab.stargram.domain.user.service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.flab.stargram.domain.common.service.CommonService;
import com.flab.stargram.domain.common.exception.DuplicateException;
import com.flab.stargram.domain.common.exception.InvalidPasswordException;
import com.flab.stargram.domain.common.exception.UserNotFoundException;
import com.flab.stargram.domain.user.model.ApiResponseEnum;
import com.flab.stargram.domain.user.model.LoginDto;
import com.flab.stargram.domain.user.model.SignUpRequestDto;
import com.flab.stargram.domain.user.model.User;

@Service
public class UserService {

    private final CommonService commonService;

    public UserService(CommonService commonService) {
        this.commonService = commonService;
    }

    @Transactional
    public User signUp(SignUpRequestDto dto) {
        if (existsByUserName(dto)) {
            throw new DuplicateException(ApiResponseEnum.DUPLICATE_USERNAME);
        } else if (existsByEmail(dto)) {
            throw new DuplicateException(ApiResponseEnum.DUPLICATE_EMAIL);
        }

        User user = new User(dto);
        commonService.save(user);
        return user;
    }

    @Transactional
    public User login(LoginDto dto) {
        Optional<User> byUserName = findByUsername(dto);
        if (byUserName.isEmpty()) {
            throw new UserNotFoundException(ApiResponseEnum.USER_NOT_FOUND);
        }

        User user = byUserName.get();
        if (!user.isCorrectPassword(dto)) {
            throw new InvalidPasswordException(ApiResponseEnum.INVALID_PASSWORD);
        }

        user.recordSuccessfulLogin();
        return user;
    }



    private boolean existsByUserName(SignUpRequestDto dto) {
        return commonService.existsByUserName(dto.getUserName());
    }

    private boolean existsByEmail(SignUpRequestDto dto) {
        return commonService.existsByEmail(dto.getEmail());
    }

    private Optional<User> findByUsername(LoginDto dto) {
        return commonService.findByUserName(dto.getUserName());
    }
}
