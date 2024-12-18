package com.f_lab.Stargram.domain.user.controller;

import com.f_lab.Stargram.model.LoginRequestDto;
import com.f_lab.Stargram.model.RegisterRequestDto;
import com.f_lab.Stargram.domain.user.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody RegisterRequestDto dto) {
        String result = userService.registerUser(dto);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody LoginRequestDto dto) {
        String result = userService.loginUser(dto);
        return ResponseEntity.ok(result);
    }
}
