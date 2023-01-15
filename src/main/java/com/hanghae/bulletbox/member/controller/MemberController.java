package com.hanghae.bulletbox.member.controller;

import com.hanghae.bulletbox.common.response.Response;
import com.hanghae.bulletbox.member.dto.MemberDto;
import com.hanghae.bulletbox.member.dto.RequestLoginDto;
import com.hanghae.bulletbox.member.dto.RequestSignupDto;
import com.hanghae.bulletbox.member.dto.VerifyCodeDto;
import com.hanghae.bulletbox.member.service.MailService;
import com.hanghae.bulletbox.member.service.MemberService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import springfox.documentation.annotations.ApiIgnore;

import lombok.RequiredArgsConstructor;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static com.hanghae.bulletbox.common.exception.ExceptionMessage.DIFFERENT_CODE_MSG;

@Tag(name = "Member", description = "회원 API")
@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    private final MailService mailService;

    @Operation(tags = {"Member"}, summary = "회원가입")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "회원가입이 완료되었습니다."),
            @ApiResponse(responseCode = "400", description = "이미 가입된 이메일입니다.")
    })
    @PostMapping("/signup")
    public Response<?> signup(@Validated @RequestBody RequestSignupDto requestSignupDto){

        MemberDto memberDto = MemberDto.toMemberDto(requestSignupDto);
        memberService.signup(memberDto);

        return Response.success(201, "회원가입이 완료되었습니다.", null);
    }

    @Operation(tags = {"Member"}, summary = "이메일 인증")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "이메일 인증 메일이 전송되었습니다.")
    })
    @PostMapping("/signup/email-validate")
    public Response<?> mailConfirm(@ApiIgnore HttpSession httpSession, @RequestBody MemberDto memberDto) throws Exception{
        String email = memberDto.getEmail();
        String code = mailService.sendSimpleMessage(email, memberDto);
        httpSession.setAttribute("code", code);
        return Response.success(200, "이메일 인증 메일이 전송되었습니다.", null);
    }

    @Operation(tags = {"Member"}, summary = "이메일 인증 코드")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "이메일 인증이 완료되었습니다."),
            @ApiResponse(responseCode = "400", description = "인증 번호가 일치하지 않습니다.")
    })
    @PostMapping("/signup/verifycode")
    public Response<?> verifyCode(@ApiIgnore HttpSession httpSession, @RequestBody VerifyCodeDto verifyCodeDto){
        if((verifyCodeDto.getVerifyCode()).equals(httpSession.getAttribute("code"))){
        }else{
            throw new IllegalStateException(DIFFERENT_CODE_MSG.getMsg());
        }
        return Response.success(200,"이메일 인증이 완료되었습니다.", true);
    }

    @Operation(tags = {"Member"}, summary = "로그인")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "로그인이 완료되었습니다."),
            @ApiResponse(responseCode = "400", description = "이메일 또는 비밀번호를 확인해주세요."),
            @ApiResponse(responseCode = "400", description = "이메일 또는 비밀번호가 일치하지 않습니다.")
    })
    @PostMapping("/login")
    public Response<?> login(@RequestBody RequestLoginDto requestLoginDto, HttpServletResponse httpServletResponse){

        MemberDto memberDto = MemberDto.toMemberDto(requestLoginDto);
        memberService.login(memberDto, httpServletResponse);

        return Response.success(200, "로그인이 완료되었습니다.", null);
    }
}

