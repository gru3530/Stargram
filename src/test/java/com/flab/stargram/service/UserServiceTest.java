package com.flab.stargram.service;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.flab.stargram.config.exception.DataNotFoundException;
import com.flab.stargram.config.exception.DuplicateException;
import com.flab.stargram.config.exception.InvalidPasswordException;
import com.flab.stargram.entity.common.ApiResponseEnum;
import com.flab.stargram.entity.dto.LoginDto;
import com.flab.stargram.entity.dto.SignUpRequestDto;
import com.flab.stargram.entity.model.User;
import com.flab.stargram.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    private SignUpRequestDto signUpRequestDto;
    private User mockUser;

    @BeforeEach
    void setUp() {
        signUpRequestDto = SignUpRequestDto.builder()
            .userName("gru")
            .password("asdf")
            .email("gru@f-lab.com")
            .build();

        mockUser = User.createUserOf(signUpRequestDto);
    }

    @DisplayName("정상 회원가입")
    @Test
    void signup(){
        // Given
        SignUpRequestDto dto = SignUpRequestDto.builder()
            .userName("gru")
            .password("asdf")
            .email("gru@f-lab.com")
            .build();

        //when
        when(userRepository.findByUserNameOrEmail(dto.getUserName(), dto.getEmail())).thenReturn(null);
        User mockUser = User.createUserOf(signUpRequestDto);
        when(userRepository.save(any(User.class))).thenReturn(mockUser);

        User result = userService.signUp(dto);

        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(mockUser);
    }

    @DisplayName("중복된 사용자명 회원가입 시 에러 반환 테스트")
    @Test
    void signUp_error_duplicateUserName() {
        // Given
        SignUpRequestDto dto = SignUpRequestDto.builder()
            .userName(mockUser.getUserName())
            .password("asdf")
            .email("gru@f-lab.com")
            .build();

        // When
        when(userRepository.findByUserNameOrEmail(dto.getUserName(), dto.getEmail())).thenReturn(mockUser);

        // Then
        DuplicateException exception = assertThrows(DuplicateException.class, () -> userService.signUp(dto));
        assertThat(exception.getResponseEnum()).isEqualTo(ApiResponseEnum.DUPLICATE_USERNAME);

        verify(userRepository, times(1)).findByUserNameOrEmail(dto.getUserName(), dto.getEmail());
        verify(userRepository, never()).save(any(User.class));
    }

    @DisplayName("중복된 email 회원가입 시 에러 반환 테스트")
    @Test
    void signup_error_invalidEmail() {
        // Given
        SignUpRequestDto dto = SignUpRequestDto.builder()
            .userName("gru2")
            .password("asdf")
            .email(mockUser.getEmail())
            .build();

        //when
        when(userRepository.findByUserNameOrEmail(dto.getUserName(), dto.getEmail())).thenReturn(mockUser);

        //then
        DuplicateException exception = assertThrows(DuplicateException.class, () -> userService.signUp(dto));
        assertThat(exception.getResponseEnum()).isEqualTo(ApiResponseEnum.DUPLICATE_EMAIL);

        verify(userRepository, times(1)).findByUserNameOrEmail(dto.getUserName(), dto.getEmail());
        verify(userRepository, never()).save(any(User.class));
    }


    @DisplayName("정상 로그인")
    @Test
    void login() {
        LoginDto loginDto = LoginDto.builder()
            .userName("gru")
            .password("asdf")
            .build();
        // Given
        when(userRepository.findByUserName(loginDto.getUserName())).thenReturn(mockUser);

        // When
        User result = userService.login(loginDto);

        // Then
        assertNotNull(result);
        assertThat(result.getUserName()).isEqualTo("gru");
        verify(userRepository, times(1)).findByUserName(loginDto.getUserName());
    }

    @DisplayName("존재하지 않는 사용자 ID로 로그인 시 에러반환 테스트")
    @Test
    void login_error_NotExistId(){
        // Given
        LoginDto dto = LoginDto.builder()
            .userName("josh")
            .password("123asd")
            .build();

        //when
        when(userRepository.findByUserName(dto.getUserName())).thenReturn(null);


        //then
        DataNotFoundException exception = assertThrows(DataNotFoundException.class, () -> userService.login(dto));
        assertThat(exception.getResponseEnum()).isEqualTo(ApiResponseEnum.USER_NOT_FOUND);
    }

    @DisplayName("로그인중 비밀번호 오류 시 에러 반환 테스트")
    @Test
    void login_error_incorrectPassword(){
        // Given
        LoginDto dto = LoginDto.builder()
            .userName("gru")
            .password("123asd")
            .build();

        //when
        when(userRepository.findByUserName(dto.getUserName())).thenReturn(mockUser);

        //then
        InvalidPasswordException exception = assertThrows(InvalidPasswordException.class, () -> userService.login(dto));
        assertThat(exception.getResponseEnum()).isEqualTo(ApiResponseEnum.INVALID_PASSWORD);
    }
}