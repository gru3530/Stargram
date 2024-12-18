package com.f_lab.Stargram.model.response;

import com.f_lab.Stargram.common.config.ResponseEnum;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ApiResponse<T> {
    private boolean success;
    private int code;
    private String message;
    private T data;

    private ApiResponse(ResponseEnum responseEnum, T data) {
        this.code = responseEnum.getCode();
        this.message = responseEnum.getMessage();
        this.data = data;
    }

    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(ResponseEnum.SUCCESS, data);
    }

    public static ApiResponse<Void> fail(ResponseEnum responseEnum) {
        return new ApiResponse<>(responseEnum, null);
    }
}
