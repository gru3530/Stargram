package com.flab.stargram.domain.user.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.flab.stargram.domain.user.model.LoginDto;
import com.flab.stargram.domain.user.model.SignUpRequestDto;
import com.flab.stargram.domain.user.model.ApiResponseDto;
import com.flab.stargram.domain.user.service.UserService;

@RestController
public class UserController {

	private final UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	@PostMapping("/signup")
	public ApiResponseDto signUp(@RequestBody SignUpRequestDto dto) {
		return new ApiResponseDto(userService.signUp(dto));
	}

	@PostMapping("/users/login")
	public ApiResponseDto login(@RequestBody LoginDto dto) {
		return new ApiResponseDto(userService.login(dto));
	}
}
