package com.flab.stargram.domain.user.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignUpRequestDto extends BaseDto{
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
