package com.flab.stargram.entity.model;

import com.flab.stargram.entity.common.BaseDto;

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
