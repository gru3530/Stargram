package com.flab.stargram.domain.user.service;

import java.util.Date;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.flab.stargram.domain.user.model.LoginDto;
import com.flab.stargram.domain.user.model.SignUpRequestDto;
import com.flab.stargram.domain.user.model.ApiResponse;
import com.flab.stargram.domain.user.model.User;
import com.flab.stargram.domain.user.repository.UserRepository;

@Service
public class UserService {
	private final UserRepository userRepository;

	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public ApiResponse signUp(SignUpRequestDto dto) {
		if (userRepository.existsByUserName(dto.getUserName())) {
			return ApiResponse.DUPLICATE_USERNAME;
		} else if (userRepository.existsByEmail(dto.getEmail())) {
			return ApiResponse.DUPLICATE_EMAIL;
		}
		userRepository.save(new User(dto));
		return ApiResponse.SUCCESS;
	}

	public ApiResponse login(LoginDto dto) {
		if(UserValidator.isUserNameValid(dto.getUserName()) == false) {
			return ApiResponse.EMPTY_USERNAME;
		} else if(UserValidator.isPasswordValid(dto.getPassword()) == false) {
			return ApiResponse.EMPTY_PASSWORD;
		}

		Optional<User> byUserName = userRepository.findByUserName(dto.getUserName());
		if (byUserName.isEmpty()) {
			return ApiResponse.USER_NOT_FOUND;
		}

		User user = byUserName.get();
		if (user.getPassword().equals(dto.getPassword()) == false) {
			return ApiResponse.INVALID_PASSWORD;
		}

		try {
			user.setLoggedInAt(new Date());
			userRepository.save(user);
			return ApiResponse.SUCCESS;
		} catch (Exception e) {
			return ApiResponse.FAILURE;
		}

	}
}
