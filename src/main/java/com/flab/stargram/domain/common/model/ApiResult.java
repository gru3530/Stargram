package com.flab.stargram.domain.common.model;

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

	public static ResponseEntity<ApiResult> success(Object data) {
		ApiResult result = new ApiResult(ApiResponseEnum.SUCCESS, null, data);
		return ResponseEntity.ok(result);
	}

	public static ResponseEntity<ApiResult> error(ApiResponseEnum apiResponseEnum, String message) {
		ApiResult result = new ApiResult(apiResponseEnum, message, null);
		return ResponseEntity.status(apiResponseEnum.getHttpStatus()).body(result);
	}

}
