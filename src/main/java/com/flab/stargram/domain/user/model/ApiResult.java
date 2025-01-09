package com.flab.stargram.domain.user.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ApiResult<T> {
	private final ApiResponseEnum code;
	private final String message;
	private final T data;

	public static <T> ApiResult<T> success(T data) {
		ApiResponseEnum apiResponseEnum = ApiResponseEnum.SUCCESS;
		return new ApiResult<>(apiResponseEnum, apiResponseEnum.getMessage(), data);
	}

	public static <T> ApiResult<T> error(ApiResponseEnum apiResponseEnum, String message) {
		return new ApiResult<>(apiResponseEnum, message, null);
	}

}
