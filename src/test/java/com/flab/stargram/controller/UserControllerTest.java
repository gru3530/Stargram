package com.flab.stargram.controller;

import static org.mockito.Mockito.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;

import com.flab.stargram.domain.auth.service.AuthCookieService;
import com.flab.stargram.domain.auth.service.AuthService;
import com.flab.stargram.entity.dto.LoginDto;
import com.flab.stargram.entity.dto.SignUpRequestDto;
import com.flab.stargram.entity.model.User;
import com.flab.stargram.service.UserService;

@TestConfiguration
class UserControllerTestConfig {
    @Bean
    public UserService userService() {
        return mock(UserService.class);
    }

    @Bean
    public AuthService authService() {
        return mock(AuthService.class);
    }

    @Bean
    public AuthCookieService authCookieService() {
        return mock(AuthCookieService.class);
    }
}

@WebMvcTest(controllers = UserController.class)
@Import(UserControllerTestConfig.class)
class UserControllerTest extends BaseMockMvcTest {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthService authService;

    @Autowired
    private AuthCookieService authCookieService;

    @DisplayName("User Api 회원가입을 수행한다")
    @Test
    void signUp() throws Exception {
        // Given
        SignUpRequestDto requestDto = SignUpRequestDto.builder()
            .userName("test")
            .email("test@f-lab.com")
            .password("testpassword")
            .build();

        User mockUser = User.createUserOf(requestDto);
        when(userService.signUp(any(SignUpRequestDto.class))).thenReturn(mockUser);

        // When & Then
        mockMvc.perform(post("/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
            .andExpect(status().isOk())
            .andDo(restDocs.document(
                requestFields(
                    fieldWithPath("userName").description("사용자 이름"), // 기존 "nickname"을 "userName"으로 변경
                    fieldWithPath("email").description("사용자 이메일"),
                    fieldWithPath("password").description("비밀번호")
                ),
                responseFields(
                    fieldWithPath("code").description("응답 코드"),
                    fieldWithPath("data.id").description("회원 ID"),
                    fieldWithPath("data.userName").description("사용자 이름"), // 기존 "nickname"을 "userName"으로 변경
                    fieldWithPath("data.email").description("사용자 이메일"),
                    fieldWithPath("data.password").description("비밀번호"),
                    fieldWithPath("data.userType").description("사용자 유형"),
                    fieldWithPath("data.loggedInAt").description("로그인 시간")
                )
            ));
    }

    @DisplayName("User Api 로그인을 수행한다")
    @Test
    void login() throws Exception {
        // Given
        SignUpRequestDto requestDto = SignUpRequestDto.builder()
            .userName("test")
            .email("test@f-lab.com")
            .password("testpassword")
            .build();

        User user = User.createUserOf(requestDto);

        when(userService.login(any(LoginDto.class))).thenReturn(user);
        when(authService.generateToken(any(User.class))).thenReturn("mockToken");

        // When & Then
        mockMvc.perform(post("/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
            .andExpect(status().isOk())
            .andDo(restDocs.document(
                requestFields(
                    fieldWithPath("userName").description("사용자 이름"),
                    fieldWithPath("email").description("사용자 이메일"),
                    fieldWithPath("password").description("비밀번호")
                ),
                responseFields(
                    fieldWithPath("code").description("응답 코드"),
                    fieldWithPath("data.id").description("회원 ID"),
                    fieldWithPath("data.userName").description("사용자 이름"),
                    fieldWithPath("data.email").description("사용자 이메일"),
                    fieldWithPath("data.password").description("비밀번호"),
                    fieldWithPath("data.userType").description("사용자 유형"),
                    fieldWithPath("data.loggedInAt").description("로그인 시간")
                )
            ));
    }

    @DisplayName("User Api 로그아웃을 수행한다")
    @Test
    void logout() throws Exception {
        // Given
        doNothing().when(authCookieService).removeAuthCookie(any());

        // When & Then
        mockMvc.perform(post("/users/logout")
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andDo(restDocs.document(
                responseFields(
                    fieldWithPath("code").description("요청 성공 여부")
                )
            ));
    }
}