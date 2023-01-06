package com.hanghae.bulletbox.member.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

@Getter
@NoArgsConstructor
public class RequestSignupDto {

    @Email
    private String email;

    private String nickname;

    @Pattern
    (regexp="(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,25}")
    private String password;
    private String profileImgUrl;

    @Builder
    public RequestSignupDto(String email, String nickname, String password, String profileImgUrl) {
        this.email = email;
        this.nickname = nickname;
        this.password = password;
        this.profileImgUrl = profileImgUrl;
    }

}
