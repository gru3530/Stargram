package com.flab.stargram.entity.model;

import com.flab.stargram.entity.common.BaseDto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignUpRequestDto extends BaseDto {
    private String userName;
    private String email;
    private String password;

    public boolean isUserNameEmpty(){
        return isFieldEmpty(userName);
    }

    public boolean isEmailEmpty(){
        return isFieldEmpty(email);
    }

    public boolean isPasswordEmpty(){
        return isFieldEmpty(password);
    }
}
