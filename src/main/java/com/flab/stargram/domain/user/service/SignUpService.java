package com.flab.stargram.domain.user.service;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.flab.stargram.domain.user.model.RegisterRequestDto;
import com.flab.stargram.domain.user.model.User;
import com.flab.stargram.domain.user.repository.UserRepository;

@Service
public class SignUpService {
	private final UserRepository userRepository;

	public SignUpService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public User signUp(RegisterRequestDto dto) {
		User user = new User();
		user.setUserName(dto.getUserName());
		user.setEmail(dto.getEmail());
		user.setPassword(dto.getPassword());
		user.setCreatedAt(new Date());
		user.setUpdatedAt(new Date());

		return userRepository.save(user);
	}

}
