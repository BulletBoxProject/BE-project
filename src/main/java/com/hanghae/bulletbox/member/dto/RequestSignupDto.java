package com.hanghae.bulletbox.member.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

@Schema(description = "회원가입 요청 Dto")
@Getter
@NoArgsConstructor
public class RequestSignupDto {

    @Schema(description = "이메일", type = "String", example = "abc@hello.world")
    @Email
    private String email;

    @Schema(description = "닉네임", type = "String", example = "닉네임")
    private String nickname;

    @Schema(description = "비밀번호", type = "String", example = "a!1234567", minLength = 8, maxLength = 25)
    @Pattern
    (regexp="^(?=.*[A-Za-z])(?=.*\\d)(?=.*[!@#$%^&*()<>])[A-Za-z\\d!@#$%^&*()<>]{8,25}$")
    private String password;

    @Schema(description = "프로필", type = "String", example = "img_url...")
    private String profileImgUrl;


    public RequestSignupDto(String email, String nickname, String password, String profileImgUrl) {
        this.email = email;
        this.nickname = nickname;
        this.password = password;
        this.profileImgUrl = profileImgUrl;
    }

}
