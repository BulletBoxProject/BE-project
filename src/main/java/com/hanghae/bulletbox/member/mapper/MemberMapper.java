package com.hanghae.bulletbox.member.mapper;

import com.hanghae.bulletbox.member.dto.LoginDto;
import com.hanghae.bulletbox.member.dto.RequestLoginDto;
import com.hanghae.bulletbox.member.dto.RequestSignupDto;
import com.hanghae.bulletbox.member.dto.SignupDto;
import com.hanghae.bulletbox.member.entity.Member;

public class MemberMapper {

    public static Member toMember(SignupDto signupDto, String password){
        return Member.builder()
                .email(signupDto.getEmail())
                .password(password)
                .nickname(signupDto.getNickname())
                .profileImgUrl(signupDto.getProfileImgUrl())
                .build();
    }

    public static SignupDto toSignupDto(RequestSignupDto requestSignupDto){
        return SignupDto.builder()
                .email(requestSignupDto.getEmail())
                .password(requestSignupDto.getPassword())
                .nickname(requestSignupDto.getNickname())
                .profileImgUrl(requestSignupDto.getProfileImgUrl())
                .build();
    }

    public static LoginDto toLoginDto(RequestLoginDto requestLoginDto){
        return LoginDto.builder()
                .email(requestLoginDto.getEmail())
                .password(requestLoginDto.getPassword())
                .build();
    }
}
