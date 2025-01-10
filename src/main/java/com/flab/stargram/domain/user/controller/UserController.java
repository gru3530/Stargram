package com.flab.stargram.domain.user.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flab.stargram.domain.common.exception.EmptyInputException;
import com.flab.stargram.domain.common.model.ApiResponseEnum;
import com.flab.stargram.domain.user.model.LoginDto;
import com.flab.stargram.domain.user.model.SignUpRequestDto;
import com.flab.stargram.domain.common.model.ApiResult;
import com.flab.stargram.domain.user.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

	private final UserService userService;

	@Transactional
	@PostMapping("/signup")
	public ResponseEntity<ApiResult> signUp(@RequestBody SignUpRequestDto dto) {
		if (dto.isUserNameEmpty()) {
			throw new EmptyInputException(ApiResponseEnum.EMPTY_USERNAME);
		}

		if (dto.isEmailEmpty()) {
			throw new EmptyInputException(ApiResponseEnum.EMPTY_EMAIL);
		}

		if (dto.isPasswordEmpty()) {
			throw new EmptyInputException(ApiResponseEnum.EMPTY_PASSWORD);
		}

		return ApiResult.success(userService.signUp(dto));
	}

	@Transactional
	@PostMapping("/login")
	public ResponseEntity<ApiResult> login(@RequestBody LoginDto dto) {
		if (dto.isUserNameEmpty()) {
			throw new EmptyInputException(ApiResponseEnum.EMPTY_USERNAME);
		}

		if (dto.isPasswordEmpty()) {
			throw new EmptyInputException(ApiResponseEnum.EMPTY_PASSWORD);
		}

		return ApiResult.success(userService.login(dto));
	}


}
