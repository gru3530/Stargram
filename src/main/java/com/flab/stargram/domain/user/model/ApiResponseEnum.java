package com.flab.stargram.domain.user.model;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public enum ApiResponseEnum {
	SUCCESS(HttpStatus.OK,null),
	EMPTY_USERNAME(HttpStatus.BAD_REQUEST, "유저 이름이 비어있습니다"),
	EMPTY_EMAIL( HttpStatus.BAD_REQUEST,"이메일이 비어있습니다"),
	EMPTY_PASSWORD( HttpStatus.BAD_REQUEST,"비밀번호가 비어있습니디"),
	DUPLICATE_USERNAME(HttpStatus.CONFLICT, "중복된 유저이름입니다"),
	DUPLICATE_EMAIL(HttpStatus.CONFLICT, "중복된 이메일 주소입니다"),
	USER_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 유저를 찾을 수 없습니다"),
	INVALID_PASSWORD(HttpStatus.UNAUTHORIZED, "비밀번호가 틀립니다"),
	EMPTY_CONTENT(HttpStatus.BAD_REQUEST,"작성된 내용이 없습니다"),
	EMPTY_USERID(HttpStatus.BAD_REQUEST,"유저ID가 비어있습니다"),

	FAILURE(HttpStatus.INTERNAL_SERVER_ERROR,"요청 처리중 작업에 실패하였습니다");

	private final String message;
	private final HttpStatus httpStatus;

	ApiResponseEnum(HttpStatus status, String message) {
		this.httpStatus = status;
		this.message = message;
	}
}
