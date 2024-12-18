package com.f_lab.Stargram.domain.user.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateProfileRequestDto {
    private String username;
    private String email;
    private String profileImageUrl;
}
