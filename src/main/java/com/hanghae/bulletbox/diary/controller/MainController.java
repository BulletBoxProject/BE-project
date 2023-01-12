package com.hanghae.bulletbox.diary.controller;

import com.hanghae.bulletbox.common.response.Response;
import com.hanghae.bulletbox.common.security.UserDetailsImpl;
import com.hanghae.bulletbox.diary.dto.ResponseChangeCalendarDto;
import com.hanghae.bulletbox.diary.dto.ResponseMainDto;
import com.hanghae.bulletbox.diary.service.MainService;

import lombok.RequiredArgsConstructor;

import org.springframework.security.core.annotation.AuthenticationPrincipal;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/main")
public class MainController {

    private final MainService mainService;

    @GetMapping
    public Response showMainPage(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        Long memberId = userDetails.getMember().getMemberId();
        ResponseMainDto responseMainDto = mainService.showMainPage(memberId);
        return Response.success(200, "메인 페이지 조회를 성공했습니다.", responseMainDto);
    }

    @GetMapping("/calendar/{todoYear}/{todoMonth}")
    public Response changeCalendar(@PathVariable Long todoYear,
                                   @PathVariable Long todoMonth,
                                   @AuthenticationPrincipal UserDetailsImpl userDetails)
    {
        Long memberId = userDetails.getMember().getMemberId();
        ResponseChangeCalendarDto responseChangeCalendarDto = mainService.changeCalendar(todoYear, todoMonth, memberId);
        return Response.success(200, "달력의 날짜 변경 조회를 성공했습니다.", responseChangeCalendarDto);
    }
}
