package com.flab.stargram.domain.user.model;

import lombok.Getter;

@Getter
public enum ApiResponse {
	SUCCESS(true, 0, "Success"),
	EMPTY_USERNAME(false, 100, "Empty Username"),
	EMPTY_EMAIL(false, 101, "Empty Email"),
	EMPTY_PASSWORD(false, 102, "Empty Password"),
	DUPLICATE_USERNAME(false, 103, "Duplicate username"),
	DUPLICATE_EMAIL(false, 104, "Duplicate email"),
	USER_NOT_FOUND(false, 105, "User Not Found"),
	INVALID_PASSWORD(false, 106, "Invalid Password"),
	FAILURE(false, 107,"login Failure");

	private final boolean isSuccess;
	private final int code;
	private final String message;


	ApiResponse(boolean isSuccess, int code, String message) {
		this.isSuccess = isSuccess;
		this.code = code;
		this.message = message;
	}
}
