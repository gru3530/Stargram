package com.flab.stargram.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.flab.stargram.domain.auth.service.AuthCookieService;
import com.flab.stargram.domain.auth.service.AuthService;
import com.flab.stargram.entity.dto.LoginDto;
import com.flab.stargram.entity.dto.SignUpRequestDto;
import com.flab.stargram.entity.common.ApiResult;
import com.flab.stargram.entity.model.User;
import com.flab.stargram.service.UserService;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class UserController {

	private final UserService userService;
	private final AuthService authService;
	private final AuthCookieService authCookieService;

	@PostMapping("/signup")
	public ResponseEntity<ApiResult> signUp(@RequestBody SignUpRequestDto dto) {
		dto.validateEmpty().ifInvalidThrow();

		return ApiResult.success(userService.signUp(dto));
	}

	@PostMapping("/login")
	public ResponseEntity<ApiResult> login(@RequestBody LoginDto dto, HttpServletResponse response) {
		dto.validateEmpty().ifInvalidThrow();

		User user = userService.login(dto);
		String token = authService.generateToken(user);
		authCookieService.addAuthCookie(response, token);

		return ApiResult.success(user);
	}

	@PostMapping("/users/logout")
	public ResponseEntity<ApiResult> logout(HttpServletResponse response) {
		authCookieService.removeAuthCookie(response);
		return ApiResult.success(null);
	}
}
