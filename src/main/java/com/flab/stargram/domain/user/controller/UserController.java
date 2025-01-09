package com.flab.stargram.domain.user.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flab.stargram.domain.user.exception.EmptyInputException;
import com.flab.stargram.domain.user.model.ApiResponseEnum;
import com.flab.stargram.domain.user.model.LoginDto;
import com.flab.stargram.domain.user.model.SignUpRequestDto;
import com.flab.stargram.domain.user.model.ApiResult;
import com.flab.stargram.domain.user.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

	private final UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	@PostMapping("/signup")
	public ApiResult<?> signUp(@RequestBody SignUpRequestDto dto) {
		if (dto.isUserNameEmpty()) {
			throw new EmptyInputException(ApiResponseEnum.EMPTY_USERNAME);
		} else if (dto.isEmailEmpty()) {
			throw new EmptyInputException(ApiResponseEnum.EMPTY_EMAIL);
		} else if (dto.isPasswordEmpty()) {
			throw new EmptyInputException(ApiResponseEnum.EMPTY_PASSWORD);
		}

		return ApiResult.success(userService.signUp(dto));
	}

	@PostMapping("/login")
	public ApiResult<?> login(@RequestBody LoginDto dto) {
		if (dto.isUserNameEmpty()) {
			throw new EmptyInputException(ApiResponseEnum.EMPTY_USERNAME);
		} else if (dto.isPasswordEmpty()) {
			throw new EmptyInputException(ApiResponseEnum.EMPTY_PASSWORD);
		}

		return ApiResult.success(userService.login(dto));
	}
}
