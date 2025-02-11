package com.flab.stargram.entity.model;

import java.time.LocalDateTime;

import com.flab.stargram.entity.common.BaseEntity;
import com.flab.stargram.entity.dto.LoginDto;
import com.flab.stargram.entity.dto.SignUpRequestDto;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String userName;

    @Email
    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    private LocalDateTime loggedInAt;

    private User(SignUpRequestDto dto){
        this.userName = dto.getUserName();
        this.email = dto.getEmail();
        this.password = dto.getPassword();
    }

    public static User createUserOf(SignUpRequestDto dto){
        return new User(dto);
    }

    public boolean isCorrectPassword(LoginDto dto){
        return this.getPassword().equals(dto.getPassword());
    }

    public void recordSuccessfulLogin() {
        this.loggedInAt = LocalDateTime.now();
    }
}