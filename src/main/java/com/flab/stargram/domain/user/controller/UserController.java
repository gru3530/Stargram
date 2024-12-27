package com.flab.stargram.domain.user.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flab.stargram.domain.user.model.ApiResponse;
import com.flab.stargram.domain.user.model.LoginDto;
import com.flab.stargram.domain.user.model.SignUpRequestDto;
import com.flab.stargram.domain.user.model.ApiResponseDto;
import com.flab.stargram.domain.user.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

	private final UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	@PostMapping("/signup")
	public ApiResponseDto signUp(@RequestBody SignUpRequestDto dto) {
		if (dto.isUserNameEmpty()) {
			new ApiResponseDto(ApiResponse.EMPTY_USERNAME);
		} else if (dto.isEmailEmpty()) {
			new ApiResponseDto(ApiResponse.EMPTY_EMAIL);
		} else if (dto.isPasswordEmpty()) {
			new ApiResponseDto(ApiResponse.EMPTY_PASSWORD);
		}

		return new ApiResponseDto(userService.signUp(dto));
	}

	@PostMapping("/login")
	public ApiResponseDto login(@RequestBody LoginDto dto) {
		if (dto.isUserNameEmpty()) {
			new ApiResponseDto(ApiResponse.EMPTY_USERNAME);
		} else if (dto.isPasswordEmpty()) {
			new ApiResponseDto(ApiResponse.EMPTY_PASSWORD);
		}

		return new ApiResponseDto(userService.login(dto));
	}
}
