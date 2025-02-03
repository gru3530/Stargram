package com.flab.stargram.entity.common;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public enum ApiResponseEnum {
    SUCCESS(HttpStatus.OK, null),
    EMPTY_USERNAME(HttpStatus.BAD_REQUEST, "유저 이름이 비어있습니다"),
    EMPTY_EMAIL(HttpStatus.BAD_REQUEST, "이메일이 비어있습니다"),
    EMPTY_PASSWORD(HttpStatus.BAD_REQUEST, "비밀번호가 비어있습니디"),
    DUPLICATE_USERNAME(HttpStatus.CONFLICT, "중복된 유저이름입니다"),
    DUPLICATE_EMAIL(HttpStatus.CONFLICT, "중복된 이메일 주소입니다"),
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 유저를 찾을 수 없습니다"),
    INVALID_PASSWORD(HttpStatus.UNAUTHORIZED, "비밀번호가 틀립니다"),
    EMPTY_CONTENT(HttpStatus.BAD_REQUEST, "작성된 내용이 없습니다"),
    EMPTY_USERID(HttpStatus.BAD_REQUEST, "유저ID가 비어있습니다"),
    EMPTY_POSTID(HttpStatus.BAD_REQUEST, "PostId가 비어있습니다"),
    POST_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 게시물을 찾을 수 없습니다"),
    NESTED_COMMENT(HttpStatus.BAD_REQUEST, "대댓글을 작성할 수 없습니다"),
    COMMENT_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 댓글을 찾을 수 없습니다"),
	INVALID_INPUT(HttpStatus.BAD_REQUEST, "잘못된 입력입니다"),
    EMPTY_FOLLOWING_ID(HttpStatus.BAD_REQUEST, "팔로우할 ID가 비어있습니다"),
    ALREADY_FOLLOWING(HttpStatus.BAD_REQUEST, "이미 팔로우 중입니다"),
    USER_NOT_FOLLOWED(HttpStatus.NOT_FOUND, "팔로우중이 아닌 유저입니다"),
    FAILURE(HttpStatus.INTERNAL_SERVER_ERROR, "요청 처리중 작업에 실패하였습니다");

    private final String message;
    private final HttpStatus httpStatus;

    ApiResponseEnum(HttpStatus status, String message) {
        this.httpStatus = status;
        this.message = message;
    }
}
