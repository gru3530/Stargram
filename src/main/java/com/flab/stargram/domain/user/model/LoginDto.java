package com.flab.stargram.domain.user.model;

import lombok.Getter;

@Getter
public class LoginDto extends BaseDto {
	private String userName;
	private String password;

	public boolean isUserNameEmpty() {
		return isFieldEmpty(userName);
	}

	public boolean isPasswordEmpty() {
		return isFieldEmpty(password);
	}
}
