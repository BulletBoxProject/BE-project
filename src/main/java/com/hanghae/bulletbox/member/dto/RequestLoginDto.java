package com.hanghae.bulletbox.member.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RequestLoginDto {

    private String email;
    private String password;

    @Builder
    public RequestLoginDto(String email, String password) {
        this.email = email;
        this.password = password;
    }

}
