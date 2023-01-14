package com.hanghae.bulletbox.common.response;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Builder;
import lombok.Getter;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response<T> {

    private int httpStatusCode;
    private String msg;
    private T data;

    @Builder
    public Response(int httpStatusCode, String msg, T data) {
        this.httpStatusCode = httpStatusCode;
        this.msg = msg;
        this.data = data;
    }

    public static <T> Response<T> success(int httpStatusCode, String msg, T data) {
        return new Response<>(httpStatusCode, msg, data);
    }

    public static <T> Response<T> fail(int httpStatusCode, String errorMessage) {
        return new Response<>(httpStatusCode, errorMessage, null);
    }

    public static <T> Response<T> fail(int httpStatusCode, String errorMessage, T data){
        return new Response<>(httpStatusCode, errorMessage, data);
    }
}
