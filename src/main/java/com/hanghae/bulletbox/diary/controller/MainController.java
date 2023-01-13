package com.hanghae.bulletbox.diary.controller;

import com.hanghae.bulletbox.common.response.Response;
import com.hanghae.bulletbox.common.security.UserDetailsImpl;
import com.hanghae.bulletbox.diary.dto.ResponseShowCalendarDto;
import com.hanghae.bulletbox.diary.dto.ResponseShowMainPageDto;
import com.hanghae.bulletbox.diary.dto.ResponseShowDailyByCategoryDto;
import com.hanghae.bulletbox.diary.dto.ResponseShowDailyDto;
import com.hanghae.bulletbox.diary.service.MainService;
import com.hanghae.bulletbox.todo.dto.TodoDto;

import lombok.RequiredArgsConstructor;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/main")
public class MainController {

    private final MainService mainService;

    @GetMapping
    public Response showMainPage(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        Long memberId = userDetails.getMember().getMemberId();
        TodoDto todoDto = TodoDto.toTodoDto(memberId);
        ResponseShowMainPageDto responseMainDto = mainService.showMainPage(todoDto);
        return Response.success(200, "메인 페이지 조회를 성공했습니다.", responseMainDto);
    }

    @GetMapping("/calendars")
    public Response showCalendar(@RequestParam(value = "year") Long year,
                                 @RequestParam(value = "month") Long month,
                                 @AuthenticationPrincipal UserDetailsImpl userDetails)
    {
        Long memberId = userDetails.getMember().getMemberId();
        TodoDto todoDto = TodoDto.toTodoDto(memberId, year, month);
        ResponseShowCalendarDto responseChangeCalendarDto = mainService.showCalendar(todoDto);
        return Response.success(200, "메인 페이지 달력 조회 날짜 변경을 성공했습니다.", responseChangeCalendarDto);
    }

    @GetMapping("/dailys")
    public Response showDaily(@RequestParam(value = "year") Long year,
                              @RequestParam(value = "month") Long month,
                              @RequestParam(value = "day") Long day,
                              @AuthenticationPrincipal UserDetailsImpl userDetails) {
        Long memberId = userDetails.getMember().getMemberId();
        TodoDto todoDto = TodoDto.toTodoDto(memberId, year, month, day);
        ResponseShowDailyDto responseShowDailyDto = mainService.showDaily(todoDto);
        return Response.success(200, "데일리 로그 날짜 변경 조회를 성공했습니다.", responseShowDailyDto);
    }

    @GetMapping("/dailys/{categoryId}")
    public Response showDailyByCategory(@PathVariable Long categoryId,
                                        @RequestParam(value = "year") Long year,
                                        @RequestParam(value = "month") Long month,
                                        @RequestParam(value = "day") Long day,
                                        @AuthenticationPrincipal UserDetailsImpl userDetails) {
        Long memberId = userDetails.getMember().getMemberId();
        TodoDto todoDto = TodoDto.toTodoDto(memberId, categoryId, year, month, day);
        ResponseShowDailyByCategoryDto responseShowDailyByCategoryDto = mainService.showDailyByCategory(todoDto);
        return Response.success(200, "카테고리별 데일리 로그 조회를 성공했습니다.", responseShowDailyByCategoryDto);
    }
}
