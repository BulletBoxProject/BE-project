package com.hanghae.bulletbox.diary.controller;

import com.hanghae.bulletbox.common.response.Response;
import com.hanghae.bulletbox.common.security.UserDetailsImpl;
import com.hanghae.bulletbox.diary.dto.ResponseDailyDto;
import com.hanghae.bulletbox.diary.service.DailyService;

import lombok.RequiredArgsConstructor;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/dailys")
public class DailyController {

    private final DailyService dailyService;

    @GetMapping
    public Response showDailyPage(@AuthenticationPrincipal UserDetailsImpl userDetails){
        Long memberId = userDetails.getMember().getMemberId();
        ResponseDailyDto responseDailyDto = dailyService.showDailyPage(memberId);
        return Response.success(200, "데일리 페이지 조회를 성공했습니다.", responseDailyDto);
    }

}