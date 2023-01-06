package com.hanghae.bulletbox.member.controller;

import com.hanghae.bulletbox.common.response.Response;
import com.hanghae.bulletbox.member.dto.RequestSignupDto;
import com.hanghae.bulletbox.member.dto.SignupDto;
import com.hanghae.bulletbox.member.mapper.MemberMapper;
import com.hanghae.bulletbox.member.service.MemberService;

import lombok.RequiredArgsConstructor;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/signup")
    public Response signup(@Validated @RequestBody RequestSignupDto requestDto){

        SignupDto signupDto = MemberMapper.toSignupDto(requestDto);
        memberService.signup(signupDto);

        return new Response(201, "회원가입이 완료되었습니다.", null);

    }
}
