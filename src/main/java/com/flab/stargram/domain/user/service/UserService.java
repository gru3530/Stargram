package com.flab.stargram.domain.user.service;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.flab.stargram.domain.user.model.SignUpRequestDto;
import com.flab.stargram.domain.user.model.SignUpResult;
import com.flab.stargram.domain.user.model.User;
import com.flab.stargram.domain.user.repository.UserRepository;

@Service
public class UserService {
	private final UserRepository userRepository;

	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public SignUpResult signUp(SignUpRequestDto dto) {
		if (userRepository.existsByUserName(dto.getUserName())) {
			return SignUpResult.DUPLICATE_USERNAME;
		} else if (userRepository.existsByEmail(dto.getEmail())) {
			return SignUpResult.DUPLICATE_EMAIL;
		}

		try {
			User user = mapDtoToEntity(dto);
			userRepository.save(user);
			return SignUpResult.SUCCESS;
		} catch (Exception e) {
			return SignUpResult.FAILURE;
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
