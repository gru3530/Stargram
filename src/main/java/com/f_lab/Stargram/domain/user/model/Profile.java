package com.f_lab.Stargram.domain.user.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Profile {
    private Long userId;
    private String username;
    private String email;
    private String profileImageUrl;
}
