package com.hanghae.bulletbox.diary.controller;

import com.hanghae.bulletbox.common.response.Response;
import com.hanghae.bulletbox.common.security.UserDetailsImpl;
import com.hanghae.bulletbox.diary.dto.MonthlyEmotionDto;
import com.hanghae.bulletbox.diary.dto.ResponseDiaryCalendarPageDto;
import com.hanghae.bulletbox.diary.dto.ResponseDiaryPageDto;
import com.hanghae.bulletbox.diary.service.DiaryPageService;
import com.hanghae.bulletbox.member.dto.MemberDto;

import lombok.RequiredArgsConstructor;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/diaries")
public class DiaryController {

    private final DiaryPageService diaryPageService;

    @GetMapping
    public Response<ResponseDiaryPageDto> showDiaryPage(@AuthenticationPrincipal UserDetailsImpl userDetails){

        MemberDto memberDto = MemberDto.toMemberDto(userDetails);

        ResponseDiaryPageDto responseDiaryPageDto = diaryPageService.showDiaryPage(memberDto);

        return Response.success(200, "일기장 페이지 조회를 성공했습니다.", responseDiaryPageDto);
    }

    @GetMapping("/calendars")
    public Response<ResponseDiaryCalendarPageDto> changeMonthOfDiaryPage(@RequestParam(value = "year") Long year,
                                                                         @RequestParam(value = "month") Long month,
                                                                         @AuthenticationPrincipal UserDetailsImpl userDetails){

        MemberDto memberDto = MemberDto.toMemberDto(userDetails);

        ResponseDiaryCalendarPageDto responseDiaryCalendarPageDto = diaryPageService.changeMonthOfDiaryPage(year, month, memberDto);

        return Response.success(200, "일기장 페이지 조회 날짜를 변경하였습니다.", responseDiaryCalendarPageDto);
    }
}
