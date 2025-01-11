package com.flab.stargram.entity.common;

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

    protected ValidationResult validationResult = new ValidationResult();

    protected abstract ValidationResult validateEmpty();
}
