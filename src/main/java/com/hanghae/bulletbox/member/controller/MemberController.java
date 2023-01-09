package com.hanghae.bulletbox.member.controller;

import com.hanghae.bulletbox.common.response.Response;
import com.hanghae.bulletbox.member.dto.MemberDto;
import com.hanghae.bulletbox.member.dto.RequestLoginDto;
import com.hanghae.bulletbox.member.dto.RequestSignupDto;
import com.hanghae.bulletbox.member.service.MemberService;

import lombok.RequiredArgsConstructor;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/signup")
    public Response<?> signup(@Validated @RequestBody RequestSignupDto requestSignupDto){

        MemberDto memberDto = MemberDto.toMemberDto(requestSignupDto);
        memberService.signup(memberDto);

        return Response.success(201, "회원가입이 완료되었습니다.", null);
    }

    @PostMapping("/login")
    public Response<?> login(@RequestBody RequestLoginDto requestLoginDto, HttpServletResponse httpServletResponse){

        MemberDto memberDto = MemberDto.toMemberDto(requestLoginDto);
        memberService.login(memberDto, httpServletResponse);

        return Response.success(200, "로그인이 완료되었습니다.", null);
    }
}
