package com.flab.stargram.domain.user.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ApiResult<T> {
	private final int code;
	private final String message;
	private final T data;

	public static <T> ApiResult<T> success(T data) {
		ApiResponseEnum apiResponseEnum = ApiResponseEnum.SUCCESS;
		return new ApiResult<>(apiResponseEnum.getCode(), apiResponseEnum.getMessage(), data);
	}

	public static <T> ApiResult<T> error(ApiResponseEnum apiResponseEnum) {
		return new ApiResult<>(apiResponseEnum.getCode(), apiResponseEnum.getMessage(), null);
	}

}
