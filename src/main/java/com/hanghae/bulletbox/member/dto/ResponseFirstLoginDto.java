package com.hanghae.bulletbox.member.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ResponseFirstLoginDto {

    private Boolean firstLogin = false;

    @Builder(access = AccessLevel.PRIVATE)
    public ResponseFirstLoginDto(Boolean firstLogin) {
        this.firstLogin = firstLogin;
    }

    public static ResponseFirstLoginDto toResponseFirstLoginDto(Boolean firstLogin) {
        return ResponseFirstLoginDto.builder()
                .firstLogin(firstLogin)
                .build();
    }

}
