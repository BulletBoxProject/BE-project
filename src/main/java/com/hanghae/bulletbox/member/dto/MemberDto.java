package com.hanghae.bulletbox.member.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Schema(description = "회원 Dto")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberDto {

    @Schema(description = "이메일", example = "email@email.com", type = "String")
    @NotNull
    private String email;

    @Schema(description = "닉네임", example = "닉네임", type = "String")
    private String nickname;

    @Schema(description = "비밀번호", type = "String", example = "a!1234567", minLength = 8, maxLength = 25)
    private String password;

    @Builder(access = AccessLevel.PRIVATE)
    private MemberDto(String email, String nickname, String password) {
        this.email = email;
        this.nickname = nickname;
        this.password = password;
    }

    public static MemberDto toMemberDto(RequestSignupDto requestSignupDto) {
        String email = requestSignupDto.getEmail();
        String nickname = requestSignupDto.getNickname();
        String password = requestSignupDto.getPassword();

        return MemberDto.builder()
                .email(email)
                .nickname(nickname)
                .password(password)
                .build();
    }

    public static MemberDto toMemberDto(RequestLoginDto requestLoginDto) {
        String email = requestLoginDto.getEmail();
        String password = requestLoginDto.getPassword();

        return MemberDto.builder()
                .email(email)
                .password(password)
                .build();
    }
}
