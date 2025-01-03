package com.flab.stargram.domain.user.model;

import lombok.Getter;

@Getter
public enum ApiResponse {
	SUCCESS(true, 0, "Success"),

	EMPTY_USERNAME(100, "Empty Username"),
	EMPTY_EMAIL(101, "Empty Email"),
	EMPTY_PASSWORD(102, "Empty Password"),
	DUPLICATE_USERNAME(103, "Duplicate username"),
	DUPLICATE_EMAIL(104, "Duplicate email"),
	USER_NOT_FOUND(105, "User Not Found"),
	INVALID_PASSWORD(106, "Invalid Password"),
	FAILURE(107,"login Failure");

	private final boolean isSuccess;
	private final int code;
	private final String message;


	ApiResponse(boolean isSuccess, int code, String message) {
		this.isSuccess = isSuccess;
		this.code = code;
		this.message = message;
	}

	ApiResponse(int code, String message) {
		this.isSuccess = false;
		this.code = code;
		this.message = message;
	}
}
