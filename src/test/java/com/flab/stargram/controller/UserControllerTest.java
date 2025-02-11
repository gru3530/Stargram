package com.flab.stargram.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.flab.stargram.entity.dto.LoginDto;
import com.flab.stargram.entity.dto.SignUpRequestDto;
import com.flab.stargram.entity.model.User;
import com.flab.stargram.service.UserService;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @Mock
    private UserService userService;

    private SignUpRequestDto signUpRequestDto;
    private LoginDto loginDto;
    private User mockUser;

    @BeforeEach
    void setUp() {
        signUpRequestDto = new SignUpRequestDto();
        signUpRequestDto.setUserName("gru");
        signUpRequestDto.setPassword("asdf");
        signUpRequestDto.setEmail("gru@f-lab.com");

        loginDto = new LoginDto();
        loginDto.setUserName("gru");
        loginDto.setPassword("asdf");

        mockUser = User.createUserOf(signUpRequestDto);
    }

    @Test
    void login() {
        // Given
        when(userService.login(any(LoginDto.class))).thenReturn(mockUser);

        // When
        User loggedInUser = userService.login(loginDto);

        // Then
        assertNotNull(loggedInUser);
        assertEquals("gru", loggedInUser.getUserName());
        verify(userService, times(1)).login(loginDto);
    }
}