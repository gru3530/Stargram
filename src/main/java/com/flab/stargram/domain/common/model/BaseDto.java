package com.flab.stargram.domain.common.model;

public abstract class BaseDto {
    protected <T> boolean isFieldEmpty(T field) {
        if (field == null) {
            return true;
        }
        if (field instanceof String) {
            return ((String) field).trim().isEmpty();
        }
        return false;
    }
}
