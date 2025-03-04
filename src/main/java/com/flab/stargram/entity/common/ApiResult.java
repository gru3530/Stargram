package com.flab.stargram.entity.common;

import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ApiResult {
	private final ApiResponseEnum code;
	private final String message;
	private final Object data;

	public static ApiResult success(Object data) {
		return new ApiResult(ApiResponseEnum.SUCCESS, null, data);
	}

	public static ApiResult success() {
		return new ApiResult(ApiResponseEnum.SUCCESS, null, null);
	}

	public static ResponseEntity<ApiResult> error(ApiResponseEnum apiResponseEnum, String message) {
		ApiResult result = new ApiResult(apiResponseEnum, message, null);
		return ResponseEntity.status(apiResponseEnum.getHttpStatus()).body(result);
	}

	public static ResponseEntity<ApiResult> error(ApiResponseEnum apiResponseEnum, String message, Object detail) {
		ApiResult result = new ApiResult(apiResponseEnum, message, detail);
		return ResponseEntity.status(apiResponseEnum.getHttpStatus()).body(result);
	}
}
