package com.hanghae.bulletbox.common.exception;

import lombok.Getter;

@Getter
public enum ExceptionMessage {
    EXCEPTION_MESSAGE_SAMPLE(401,"예외 내용을 적어주세요"),

    NOT_FOUND_MEMBER_MSG(400, "존재하지 않는 사용자입니다."),

    DUPLICATE_EMAIL_MSG(400, "이미 가입한 이메일입니다."),

    NOT_FOUND_EMAIL_MSG(400,"이메일 또는 비밀번호를 확인해주세요."),

    DIFFERENT_PASSWORD_MSG(400, "이메일 또는 비밀번호가 일치하지 않습니다."),

    DIFFERENT_CODE_MSG(400, "인증번호가 일치하지 않습니다."),

    /*Jwt, Security */
    TOKEN_NOT_FOUND_MSG(401,"토큰이 존재하지 않습니다."),

    INVALID_TOKEN_MSG(401,"토큰이 유효하지 않습니다.");

    private final int statusCode;
    private final String msg;

    ExceptionMessage(final int statusCode, final String msg) {
        this.statusCode = statusCode;
        this.msg = msg;
    }
}
