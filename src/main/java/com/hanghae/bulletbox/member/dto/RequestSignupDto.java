package com.hanghae.bulletbox.member.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Getter
@NoArgsConstructor
public class RequestSignupDto {


    @NotEmpty(message = "이메일 입력은 필수입니다")
    @Email
    private String email;
    @NotEmpty(message = "닉네임 입력은 필수입니다")
    private String nickname;
    @NotEmpty(message = "패스워드 입력은 필수입니다")
    @Pattern
    (regexp="(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,25}", message = "영문, 숫자, 특수문자 최소 하나 이상 포함 8자리 이상, 25자리 이하입니다.")
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
