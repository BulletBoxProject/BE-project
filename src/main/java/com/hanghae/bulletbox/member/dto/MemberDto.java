package com.hanghae.bulletbox.member.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberDto {

    @NotNull
    private String email;

    private String nickname;

    private String password;

    private String profileImgUrl;

    public MemberDto(String email, String nickname, String password, String profileImgUrl) {
        this.email = email;
        this.nickname = nickname;
        this.password = password;
        this.profileImgUrl = profileImgUrl;
    }

    public static MemberDto toMemberDto(String email, String nickname, String password, String profileImgUrl) {
        return new MemberDto(email, nickname, password, null);
    }

    public static MemberDto toMemberDto(RequestSignupDto requestSignupDto) {
        String email = requestSignupDto.getEmail();
        String nickname = requestSignupDto.getNickname();
        String password = requestSignupDto.getPassword();
        String profileImgUrl = requestSignupDto.getProfileImgUrl();

        return new MemberDto(email, nickname, password, null);

    }

    public static MemberDto toMemberDto(RequestLoginDto requestLoginDto) {
        String email = requestLoginDto.getEmail();
        String password = requestLoginDto.getPassword();

        return new MemberDto(email, null, password, null);
    }
}
