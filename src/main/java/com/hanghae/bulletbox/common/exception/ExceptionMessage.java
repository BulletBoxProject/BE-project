package com.hanghae.bulletbox.common.exception;

import lombok.Getter;

@Getter
public enum ExceptionMessage {

    NOT_FOUND_MEMBER_MSG(404, "존재하지 않는 사용자입니다."),

    DUPLICATE_EMAIL_MSG(400, "이미 가입한 이메일입니다."),

    NOT_FOUND_EMAIL_MSG(400, "이메일 또는 비밀번호를 확인해주세요."),

    DIFFERENT_PASSWORD_MSG(400, "이메일 또는 비밀번호가 일치하지 않습니다."),

    DIFFERENT_CODE_MSG(400, "인증번호가 일치하지 않습니다."),

    FAILED_TO_SEND_MAIL(400, "메일 발송에 실패하였습니다."),

    /*Jwt, Security */
    TOKEN_NOT_FOUND_MSG(401, "토큰이 존재하지 않습니다."),

    INVALID_TOKEN_MSG(401, "토큰이 유효하지 않습니다."),

    SOCIAL_LOGIN_ERROR(401, "소셜 로그인에 실패하였습니다."),

    NOT_MATCH_REFRESH_TOKEN(401, "리프레쉬 토큰이 일치하지 않습니다."),

    // Todo관련
    BULLET_NOT_FOUND_MSG(404, "불렛이 존재하지 않습니다."),

    TODO_NOT_FOUND_MSG(404, "할 일이 존재하지 않습니다."),

    // Category
    DUPLICATE_CATEGORYNAME_MSG(400, "이미 존재하는 카테고리입니다."),

    NOT_FOUND_CATEGORY_MSG(400, "존재하지 않는 카테고리입니다.");

    private final int statusCode;
    private final String msg;

    ExceptionMessage(final int statusCode, final String msg) {
        this.statusCode = statusCode;
        this.msg = msg;
    }
}
