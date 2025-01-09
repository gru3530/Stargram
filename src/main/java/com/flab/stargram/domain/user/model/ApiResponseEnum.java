package com.flab.stargram.domain.user.model;

import lombok.Getter;

@Getter
public enum ApiResponseEnum {
	SUCCESS("요청에 성공하였습니다"),
	EMPTY_USERNAME( "유저 이름이 비어있습니다"),
	EMPTY_EMAIL( "이메일이 비어있습니다"),
	EMPTY_PASSWORD( "비밀번호가 비어있습니디"),
	DUPLICATE_USERNAME( "중복된 유저이름입니다"),
	DUPLICATE_EMAIL( "중복된 이메일 주소입니다"),
	USER_NOT_FOUND( "해당 유저를 찾을 수 없습니다"),
	INVALID_PASSWORD( "비밀번호가 틀립니다"),
	FAILURE("login Failure");

	private final String message;

	ApiResponseEnum(String message) {
		this.message = message;
	}
}
