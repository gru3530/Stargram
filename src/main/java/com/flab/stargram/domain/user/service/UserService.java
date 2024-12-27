package com.flab.stargram.domain.user.service;

import java.util.Date;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.flab.stargram.domain.user.model.LoginDto;
import com.flab.stargram.domain.user.model.SignUpRequestDto;
import com.flab.stargram.domain.user.model.ApiResponse;
import com.flab.stargram.domain.user.model.User;

@Service
public class UserService {

	private final commonService commonService;


	public UserService(commonService commonService) {
		this.commonService = commonService;
	}

	public ApiResponse signUp(SignUpRequestDto dto) {
		if (commonService.existsByUserName(dto)) {
			return ApiResponse.DUPLICATE_USERNAME;
		} else if (commonService.existsByEmail(dto)) {
			return ApiResponse.DUPLICATE_EMAIL;
		}
		commonService.save(new User(dto));
		return ApiResponse.SUCCESS;
	}

	public ApiResponse login(LoginDto dto) {
		Optional<User> byUserName = commonService.findByUserName(dto);
		if (byUserName.isEmpty()) {
			return ApiResponse.USER_NOT_FOUND;
		}

		User user = byUserName.get();
		if (!user.isCorrectPassword(dto)) {
			return ApiResponse.INVALID_PASSWORD;
		}

		//로그인 시간 업데이트를 위해 save 할 경우 updateAt 변수도 변하기 때문에 다른방식을 생각해야함
		user.setLoggedInAt(new Date());
		commonService.save(user);
		return ApiResponse.SUCCESS;

	}
}
