package com.flab.stargram.domain.user.model;

public abstract class BaseDto {
    protected boolean isFieldEmpty(String field){
        return field == null || field.trim().isEmpty();
    }
}
