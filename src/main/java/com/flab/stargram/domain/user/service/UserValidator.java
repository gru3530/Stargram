package com.flab.stargram.domain.user.service;

public class UserValidator {
	public static boolean isUserNameValid(String userName) {
		return userName != null && !userName.trim().isEmpty();
	}

	public static boolean isEmailValid(String email) {
		return email != null && !email.trim().isEmpty();
	}

	public static boolean isPasswordValid(String password) {
		return password != null && !password.isEmpty();
	}
}
