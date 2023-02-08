package com.hanghae.bulletbox.mypage.controller;

import com.hanghae.bulletbox.common.response.Response;
import com.hanghae.bulletbox.common.security.UserDetailsImpl;
import com.hanghae.bulletbox.member.entity.Member;
import com.hanghae.bulletbox.mypage.dto.MyPageDto;
import com.hanghae.bulletbox.mypage.dto.ResponseShowMyPageDto;
import com.hanghae.bulletbox.mypage.service.MyPageService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import lombok.RequiredArgsConstructor;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import springfox.documentation.annotations.ApiIgnore;

@Tag(name = "MyPage", description = "마이 페이지 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/mypages")
public class MyPageController {

    private final MyPageService myPageService;

    @Operation(tags = {"MyPage"}, summary = "마이 페이지 조회")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "마이 페이지 조회를 성공했습니다.")
    })
    @GetMapping
    public Response<ResponseShowMyPageDto> showMyPage(@ApiIgnore @AuthenticationPrincipal UserDetailsImpl userDetails) {

        Member member = userDetails.getMember();
        String email = member.getEmail();
        String nickname = member.getNickname();

        MyPageDto myPageDto = MyPageDto.toMyPageDto(email, nickname);
        ResponseShowMyPageDto responseShowMyPageDto = myPageService.showMyPage(myPageDto);

        return Response.success(200, "마이 페이지 조회를 성공했습니다.", responseShowMyPageDto);
    }
}
