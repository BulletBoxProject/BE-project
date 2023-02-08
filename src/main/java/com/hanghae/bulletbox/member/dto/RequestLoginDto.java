package com.hanghae.bulletbox.member.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Schema(description = "로그인 요청 Dto")
@Getter
@NoArgsConstructor
public class RequestLoginDto {

    @Schema(description = "이메일", example = "abc@hello.world", type = "String")
    private String email;

    @Schema(description = "비밀번호", type = "String", example = "a!1234567", minLength = 8, maxLength = 25)
    private String password;

    private RequestLoginDto(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
