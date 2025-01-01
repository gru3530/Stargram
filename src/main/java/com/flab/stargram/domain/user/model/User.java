package com.flab.stargram.domain.user.model;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.*;
import lombok.*;

@EntityListeners(AuditingEntityListener.class)
@Entity
@Getter
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String userName;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    private LocalDateTime loggedInAt;

    public User(SignUpRequestDto dto) {
        this.userName = dto.getUserName();
        this.email = dto.getEmail();
        this.password = dto.getPassword();
    }

    public boolean isCorrectPassword(LoginDto dto){
        return this.getPassword().equals(dto.getPassword());
    }
}