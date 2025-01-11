package com.flab.stargram.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flab.stargram.entity.model.LoginDto;
import com.flab.stargram.entity.model.SignUpRequestDto;
import com.flab.stargram.entity.common.ApiResult;
import com.flab.stargram.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

	private final UserService userService;

	@PostMapping("/signup")
	public ResponseEntity<ApiResult> signUp(@RequestBody SignUpRequestDto dto) {
		dto.validateEmpty().ifInvalidThrow();

		return ApiResult.success(userService.signUp(dto));
	}

	@PostMapping("/login")
	public ResponseEntity<ApiResult> login(@RequestBody LoginDto dto) {
		dto.validateEmpty().ifInvalidThrow();

		return ApiResult.success(userService.login(dto));
	}
}
