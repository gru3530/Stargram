package com.f_lab.Stargram.controller;

import com.f_lab.Stargram.dto.EmailVerificationDto;
import com.f_lab.Stargram.dto.LoginRequestDto;
import com.f_lab.Stargram.dto.RegisterStep1Dto;
import com.f_lab.Stargram.dto.RegisterStep2Dto;
import com.f_lab.Stargram.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequestDto requestDto) {
        boolean authenticated = userService.authenticateUser(requestDto.getUserName(), requestDto.getPassword());
        return authenticated ? ResponseEntity.ok("로그인 성공") : ResponseEntity.status(401).body("로그인 실패");
    }

    @PostMapping("/email")
    public ResponseEntity<String> sendEmailVerification(@RequestBody EmailVerificationDto requestDto) {
        userService.sendEmailVerificationCode(requestDto.getEmail());
        return ResponseEntity.ok("이메일 인증 코드 발송");
    }

    @GetMapping("/authentication-code")
    public ResponseEntity<String> verifyEmailCode(@RequestBody EmailVerificationDto requestDto) {
        boolean verified = userService.verifyEmail(requestDto.getEmail(), requestDto.getCode());
        return verified ? ResponseEntity.ok("이메일 인증 성공") : ResponseEntity.status(400).body("인증 코드가 올바르지 않습니다.");
    }

    @PostMapping("/users")
    public ResponseEntity<String> registerUser(@RequestParam("step") int step,
                                               @RequestBody RegisterStep1Dto step1Dto,
                                               @RequestBody(required = false) RegisterStep2Dto step2Dto) {
        if (step == 1) {
            userService.registerStep1(step1Dto.getUserName(), step1Dto.getPassword(), step1Dto.getEmail());
            return ResponseEntity.ok("회원가입 1단계 성공");
        } else if (step == 2) {
            if (step2Dto == null || step2Dto.getUserId() == null) {
                return ResponseEntity.badRequest().body("필수 정보(userId)가 없습니다.");
            }
            userService.registerStep2(step2Dto.getUserId(), step2Dto.getInterests());
            return ResponseEntity.ok("회원가입 2단계 성공");
        }
        return ResponseEntity.badRequest().body("잘못된 단계 요청");
    }
}
