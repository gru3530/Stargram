package com.flab.stargram.domain.user.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.flab.stargram.domain.user.model.LoginDto;
import com.flab.stargram.domain.user.model.SignUpRequestDto;
import com.flab.stargram.domain.user.model.User;
import com.flab.stargram.domain.user.repository.UserRepository;

@Service
public class CommonService {
    private final UserRepository userRepository;

    public CommonService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean existsByUserName(String userName) {
        return userRepository.existsByUserName(userName);
    }

    public boolean existsByEmail(String email){
        return userRepository.existsByEmail(email);
    }

    public Optional<User> findByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }

    public boolean findByUserId(Long userId) {
        return userRepository.findById(userId).isPresent();
    }

    public void save(User user){
        userRepository.save(user);
    }

}
