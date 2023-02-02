package com.hanghae.bulletbox.member.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ResponseLoginDto {

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
