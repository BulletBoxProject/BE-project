package com.hanghae.bulletbox.common.exception;

import lombok.Getter;

@Getter
public enum ExceptionMessage {
    EXCEPTION_MESSAGE_SAMPLE(401,"예외 내용을 적어주세요");
    private final int statusCode;
    private final String msg;

    ExceptionMessage(final int statusCode, final String msg) {
        this.statusCode = statusCode;
        this.msg = msg;
    }
}
