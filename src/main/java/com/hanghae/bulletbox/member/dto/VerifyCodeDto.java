package com.hanghae.bulletbox.member.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class VerifyCodeDto {

    @NotNull
    private String verifyCode;

    public VerifyCodeDto(String verifyCode) {
        this.verifyCode = verifyCode;
    }
}
