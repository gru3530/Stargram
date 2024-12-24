package com.flab.stargram.domain.user.service;

import java.util.Date;

import org.springframework.stereotype.Service;

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

		try {
			User user = mapDtoToEntity(dto);
			userRepository.save(user);
			return ApiResponse.SUCCESS;
		} catch (Exception e) {
			return ApiResponse.FAILURE;
		}
	}

	private User mapDtoToEntity(SignUpRequestDto dto) {
		User user = new User();
		user.setUserName(dto.getUserName());
		user.setEmail(dto.getEmail());
		user.setPassword(dto.getPassword());

		Date date = new Date();
		user.setCreatedAt(date);
		user.setUpdatedAt(date);
		return user;
	}
}
