package com.hanghae.bulletbox.mypage.dto;

import com.hanghae.bulletbox.member.entity.Member;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Schema(description = "마이 페이지 Dto")
public class MyPageDto {

    @Schema(description = "이메일", type = "String")
    private String email;

    @Schema(description = "닉네임", type = "String")
    private String nickname;

    @Schema(description = "회원", type = "Member")
    private Member member;

    @Builder(access = AccessLevel.PRIVATE)
    private MyPageDto(Member member, String email, String nickname) {
        this.member = member;
        this.email = email;
        this.nickname = nickname;
    }

    public static MyPageDto toMyPageDto(String email, String nickname) {
        return MyPageDto.builder()
                .email(email)
                .nickname(nickname)
                .build();
    }
}
