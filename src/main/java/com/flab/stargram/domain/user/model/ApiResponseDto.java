package com.flab.stargram.domain.user.model;

import lombok.Getter;

@Getter
public class ApiResponseDto {
	private final boolean isSuccess;
	private final int code;
	private final String message;

	public ApiResponseDto(ApiResponse apiResponse) {
		this.isSuccess = apiResponse.isSuccess();
		this.code = apiResponse.getCode();
		this.message = apiResponse.getMessage();
	}
}
