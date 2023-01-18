package com.hanghae.bulletbox.mypage.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Schema(name = "ResponseShowMyPageDto", description = "마이 페이지 조회 API 응답 DTO")
public class ResponseShowMyPageDto {

    @Schema(description = "이메일", type = "String")
    private String email;

    @Schema(description = "닉네임", type = "String")
    private String nickname;

    @Builder(access = AccessLevel.PRIVATE)
    private ResponseShowMyPageDto(String email, String nickname) {
        this.email = email;
        this.nickname = nickname;
    }

    public static ResponseShowMyPageDto toResponseShowMyPageDto(String email, String nickname) {
        return ResponseShowMyPageDto.builder()
                .email(email)
                .nickname(nickname)
                .build();
    }
}
