package com.hanghae.bulletbox.member.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Schema(description = "인증 코드 Dto")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class VerifyCodeDto {

    @Schema(description = "인증 코드", type = "String")
    @NotNull
    private String verifyCode;

    @Schema(description = "이메일", example = "email@email.com", type = "String")
    private String email;

    public VerifyCodeDto(String email, String verifyCode) {
        this.verifyCode = verifyCode;
        this.email = email;
    }
}
