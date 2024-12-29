package com.flab.stargram.domain.user.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.flab.stargram.domain.user.exception.DuplicateException;
import com.flab.stargram.domain.user.exception.InvalidPasswordException;
import com.flab.stargram.domain.user.exception.UserNotFoundException;
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

	public User signUp(SignUpRequestDto dto) {
		if (commonService.existsByUserName(dto)) {
			throw new DuplicateException(ApiResponseEnum.DUPLICATE_USERNAME);
		} else if (commonService.existsByEmail(dto)) {
			throw new DuplicateException(ApiResponseEnum.DUPLICATE_EMAIL);
		}

		User user = new User(dto);
		commonService.save(user);
		return user;
	}

	public User login(LoginDto dto) {
		Optional<User> byUserName = commonService.findByUserName(dto);
		if (byUserName.isEmpty()) {
			throw new UserNotFoundException(ApiResponseEnum.USER_NOT_FOUND);
		}

		User user = byUserName.get();
		if (!user.isCorrectPassword(dto)) {
			throw new InvalidPasswordException(ApiResponseEnum.INVALID_PASSWORD);
		}

		//로그인 시간 업데이트를 위해 save 할 경우 updateAt 변수도 변하기 때문에 다른방식을 생각해야함
		commonService.save(user);
		return user;

	}
}
