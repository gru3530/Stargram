package com.flab.stargram.domain.user.model;

import lombok.Getter;

@Getter
public enum ApiResponse {
	SUCCESS(true, 0, "Success"),
	DUPLICATE_USERNAME(false, 100, "Duplicate username"),
	DUPLICATE_EMAIL(false, 101, "Duplicate email"),
	FAILURE(false, 102,"login Failure");

	private final boolean isSuccess;
	private final int code;
	private final String message;


	ApiResponse(boolean isSuccess, int code, String message) {
		this.isSuccess = isSuccess;
		this.code = code;
		this.message = message;
	}
}
