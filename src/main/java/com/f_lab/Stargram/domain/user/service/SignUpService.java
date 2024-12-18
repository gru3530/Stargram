package com.f_lab.Stargram.domain.user.service;
import com.f_lab.Stargram.common.exception.DuplicateEmailException;
import com.f_lab.Stargram.common.exception.DuplicateUsernameException;
import com.f_lab.Stargram.common.exception.InvalidEmailTokenException;
import com.f_lab.Stargram.common.exception.InvalidUsernameTokenException;
import com.f_lab.Stargram.domain.user.model.SignUpRequestDto;
import com.f_lab.Stargram.domain.user.model.User;
import com.f_lab.Stargram.domain.user.repository.UserRepository;
import com.f_lab.Stargram.utils.SignUpRedisUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class SignUpService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final SignUpRedisUtil signUpRedisUtil;

    private static final int TOKEN_LENGTH = 7;

    @Transactional
    public void registerUser(SignUpRequestDto signUpDto) {
        checkDuplicateEmail(signUpDto.getEmail());
        checkDuplicateUsername(signUpDto.getUsername());

        validateToken(signUpDto.getEmail(), signUpDto.getEmailToken(),
                signUpDto.getUsername(), signUpDto.getUsernameToken());

        String encodedPassword = passwordEncoder.encode(signUpDto.getPassword());
        User user = User.builder()
                .email(signUpDto.getEmail())
                .userName(signUpDto.getUsername())
                .password(encodedPassword)
                .build();

        userRepository.save(user);
    }

    private void checkDuplicateEmail(String email) {
        if (userRepository.existsByEmail(email)) {
            throw new DuplicateEmailException();
        }
    }

    private void checkDuplicateUsername(String username) {
        if (userRepository.existsByUserName(username)) {
            throw new DuplicateUsernameException();
        }
    }

    private void validateToken(String email, String emailToken, String username, String usernameToken) {
        validateTokenSize(emailToken, usernameToken);
        validateEmailToken(email, emailToken);
        validateUsernameToken(username, usernameToken);
    }

    private void validateTokenSize(String emailToken, String usernameToken) {
        if (emailToken == null || emailToken.length() != TOKEN_LENGTH) {
            throw new InvalidEmailTokenException();
        }
        if (usernameToken == null || usernameToken.length() != TOKEN_LENGTH) {
            throw new InvalidUsernameTokenException();
        }
    }

    private void validateEmailToken(String email, String emailToken) {
        String storedEmailToken = signUpRedisUtil.get(email);
        if (!emailToken.equals(storedEmailToken)) {
            throw new InvalidEmailTokenException();
        }
    }

    private void validateUsernameToken(String username, String usernameToken) {
        String storedUsernameToken = signUpRedisUtil.get(username + "_verify");
        if (!usernameToken.equals(storedUsernameToken)) {
            throw new InvalidUsernameTokenException();
        }
    }
}
