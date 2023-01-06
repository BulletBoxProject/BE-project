package com.hanghae.bulletbox.member.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SignupDto {

    private String email;
    private String nickname;
    private String password;
    private String profileImgUrl;

    @Builder
    public SignupDto(String email, String nickname, String password, String profileImgUrl) {
        this.email = email;
        this.nickname = nickname;
        this.password = password;
        this.profileImgUrl = profileImgUrl;
    }
}
