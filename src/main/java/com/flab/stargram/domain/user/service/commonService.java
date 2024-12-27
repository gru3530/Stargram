package com.flab.stargram.domain.user.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.flab.stargram.domain.user.model.LoginDto;
import com.flab.stargram.domain.user.model.SignUpRequestDto;
import com.flab.stargram.domain.user.model.User;
import com.flab.stargram.domain.user.repository.UserRepository;

@Service
public class commonService {
    private final UserRepository userRepository;

    public commonService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean existsByUserName(SignUpRequestDto dto){
        return userRepository.existsByUserName(dto.getUserName());
    }

    public boolean existsByEmail(SignUpRequestDto dto){
        return userRepository.existsByEmail(dto.getEmail());
    }

    public Optional<User> findByUserName(LoginDto dto){
        return userRepository.findByUserName(dto.getUserName());
    }

    public void save(User user){
        userRepository.save(user);
    }

}
