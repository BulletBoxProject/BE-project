package com.hanghae.bulletbox.member.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Schema(description = "로그인 응답 Dto")
public class ResponseLoginDto {

    @Schema(description = "첫 로그인 판별 여부", example = "true", type = "Boolean")
    private Boolean firstLogin = true;

    @Builder(access = AccessLevel.PRIVATE)
    public ResponseLoginDto(Boolean firstLogin) {
        this.firstLogin = firstLogin;
    }

    public static ResponseLoginDto toResponseLoginDto(Boolean firstLogin) {
        return ResponseLoginDto.builder()
                .firstLogin(firstLogin)
                .build();
    }

}
