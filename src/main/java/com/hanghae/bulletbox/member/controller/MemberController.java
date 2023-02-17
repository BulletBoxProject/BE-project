package com.hanghae.bulletbox.member.controller;

import com.hanghae.bulletbox.common.response.Response;
import com.hanghae.bulletbox.member.dto.MemberDto;
import com.hanghae.bulletbox.member.dto.RequestLoginDto;
import com.hanghae.bulletbox.member.dto.RequestSignupDto;
import com.hanghae.bulletbox.member.dto.VerifyCodeDto;
import com.hanghae.bulletbox.member.service.MemberFacade;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.RequiredArgsConstructor;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Tag(name = "Member", description = "회원 API")
@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberFacade memberFacade;

    @Operation(tags = {"Member"}, summary = "회원가입")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "회원가입이 완료되었습니다."),
            @ApiResponse(responseCode = "400", description = "이미 가입된 이메일입니다.")
    })
    @PostMapping("/signup")
    public Response<RequestSignupDto> signup(@Validated @RequestBody RequestSignupDto requestSignupDto) {

        MemberDto memberDto = MemberDto.toMemberDto(requestSignupDto);

        memberFacade.signup(memberDto);

        return Response.success(201, "회원가입이 완료되었습니다.", null);
    }

    @Operation(tags = {"Member"}, summary = "이메일 인증")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "이메일 인증 메일이 전송되었습니다."),
            @ApiResponse(responseCode = "400-1", description = "이미 가입한 이메일입니다."),
            @ApiResponse(responseCode = "400-2", description = "메일 발송에 실패하였습니다."),
    })
    @PostMapping("/signup/email-validate")
    public Response<MemberDto> mailConfirm(@RequestBody VerifyCodeDto verifyCodeDto) throws Exception {

        String email = verifyCodeDto.getEmail();

        memberFacade.sendSimpleMessage(email);

        return Response.success(200, "이메일 인증 메일이 전송되었습니다.", null);
    }

    @Operation(tags = {"Member"}, summary = "이메일 인증 코드")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "이메일 인증이 완료되었습니다."),
            @ApiResponse(responseCode = "400", description = "인증 번호가 일치하지 않습니다.")
    })
    @PostMapping("/signup/verifycode")
    public Response<?> verifyCode(@RequestBody VerifyCodeDto verifyCodeDto) {

        String email = verifyCodeDto.getEmail();
        String code = verifyCodeDto.getVerifyCode();

        memberFacade.verifyCode(email, code);

        return Response.success(200, "이메일 인증이 완료되었습니다.", true);
    }

    @Operation(tags = {"Member"}, summary = "로그인")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "로그인이 완료되었습니다."),
            @ApiResponse(responseCode = "400-1", description = "이메일 또는 비밀번호를 확인해주세요."),
            @ApiResponse(responseCode = "400-2", description = "이메일 또는 비밀번호가 일치하지 않습니다.")
    })
    @PostMapping("/login")
    public Response<?> login(@RequestBody RequestLoginDto requestLoginDto, HttpServletResponse httpServletResponse) {

        MemberDto memberDto = MemberDto.toMemberDto(requestLoginDto);

        memberFacade.login(memberDto, httpServletResponse);

        return Response.success(200, "로그인이 완료되었습니다.", null);
    }

    @Operation(tags = {"Member"}, summary = "토큰 재발행")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "토큰 재발행을 성공했습니다."),
            @ApiResponse(responseCode = "401", description = "리프레쉬 토큰이 일치하지 않습니다.")
    })
    @PostMapping("/auth/token")
    public Response<?> reissueToken(HttpServletRequest request, HttpServletResponse response) {

        memberFacade.reissueToken(request, response);

        return Response.success(200, "토큰 재발행 성공", null);
    }

    @Operation(tags = {"Member"}, summary = "카카오 로그인")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "카카오 로그인을 성공했습니다."),
            @ApiResponse(responseCode = "401", description = "카카오 로그인에 실패하였습니다.")
    })
    @GetMapping("/login/kakao")
    public Response<?> kakaoLogin(@RequestParam String code, HttpServletResponse response) {

        memberFacade.kakaoLogin(code, response);

        return Response.success(200, "카카오 로그인 성공", null);
    }

    @Operation(tags = {"Member"}, summary = "카카오 로그인")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "구글 로그인을 성공했습니다."),
            @ApiResponse(responseCode = "401", description = "구글 로그인에 실패하였습니다.")
    })
    @GetMapping("/login/google")
    public Response<?> googleLogin(@RequestParam String code, HttpServletResponse response) {

        memberFacade.googleLogin(code, response);

        return Response.success(200, "구글 로그인 성공", null);
    }

    @Operation(tags = {"Member"}, summary = "테스트 로그인")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "테스트 로그인을 성공했습니다.")
    })
    @PostMapping("/login/test")
    public Response<?> testLogin(HttpServletResponse response){

        memberFacade.testLogin(response);

        return Response.success(201, "테스트 로그인 성공", null);
    }
}

