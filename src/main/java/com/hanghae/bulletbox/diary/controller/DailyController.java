package com.hanghae.bulletbox.diary.controller;

import com.hanghae.bulletbox.category.dto.ResponseCategoryDto;
import com.hanghae.bulletbox.common.response.Response;
import com.hanghae.bulletbox.common.security.UserDetailsImpl;
import com.hanghae.bulletbox.diary.dto.ResponseDailyDto;
import com.hanghae.bulletbox.diary.dto.ResponseShowDailyDto;
import com.hanghae.bulletbox.diary.service.DailyService;
import com.hanghae.bulletbox.todo.dto.TodoDto;

import lombok.RequiredArgsConstructor;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/dailys")
public class DailyController {

    private final DailyService dailyService;

    @GetMapping
    public Response showDailyPage(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        Long memberId = userDetails.getMember().getMemberId();
        ResponseDailyDto responseDailyDto = dailyService.showDailyPage(memberId);
        return Response.success(200, "데일리 페이지 조회를 성공했습니다.", responseDailyDto);
    }

    @GetMapping("/{todoYear}/{todoMonth}/{todoDay}")
    public Response showDailyPageChangeDay(@PathVariable Long todoYear, @PathVariable Long todoMonth,
                                           @PathVariable Long todoDay, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        Long memberId = userDetails.getMember().getMemberId();
        TodoDto todoDto = TodoDto.toTodoDto(memberId, todoYear, todoMonth, todoDay);
        ResponseShowDailyDto responseShowDailyDto = dailyService.showDailyPageChangeDay(todoDto);
        return Response.success(200, "데일리 로그 조회 날짜 변경을 성공했습니다.", responseShowDailyDto);
    }


    @GetMapping("/{todoYear}/{todoMonth}/{todoDay}/{categoryId}")
    public Response showDailyByCategory(@PathVariable Long todoYear, @PathVariable Long todoMonth,
                                        @PathVariable Long todoDay, @PathVariable Long categoryId,
                                        @AuthenticationPrincipal UserDetailsImpl userDetails) {

        Long memberId = userDetails.getMember().getMemberId();
        TodoDto todoDto = TodoDto.toTodoDto(memberId, categoryId, todoYear, todoMonth, todoDay);
        ResponseCategoryDto responseCategoryDto = dailyService.showDailyByCategory(todoDto);
        return Response.success(200, "카테고리별 데일리 로그 조회를 성공했습니다.", responseCategoryDto);
    }
}