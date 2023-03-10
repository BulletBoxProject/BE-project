package com.hanghae.bulletbox.main.controller;

import com.hanghae.bulletbox.common.response.Response;
import com.hanghae.bulletbox.common.security.UserDetailsImpl;
import com.hanghae.bulletbox.main.dto.ResponseShowCalendarDto;
import com.hanghae.bulletbox.main.dto.ResponseShowMainPageDto;
import com.hanghae.bulletbox.main.dto.ResponseShowDailyDto;
import com.hanghae.bulletbox.main.service.MainService;
import com.hanghae.bulletbox.member.dto.MemberDto;
import com.hanghae.bulletbox.todo.dto.TodoDto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import lombok.RequiredArgsConstructor;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Main", description = "메인 페이지 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/main")
public class MainController {

    private final MainService mainService;

    @Operation(tags = {"Main"}, summary = "메인 페이지 조회")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "메인 페이지 조회를 성공했습니다."),
            @ApiResponse(responseCode = "404", description = "존재하지 않는 사용자입니다.")
    })
    @GetMapping
    public Response<ResponseShowMainPageDto> showMainPage(@AuthenticationPrincipal UserDetailsImpl userDetails) {

        MemberDto memberDto = MemberDto.toMemberDto(userDetails);
        TodoDto todoDto = TodoDto.toTodoDto(memberDto);

        ResponseShowMainPageDto responseMainDto = mainService.showMainPage(todoDto);

        return Response.success(200, "메인 페이지 조회를 성공했습니다.", responseMainDto);
    }

    @Operation(tags = {"Main"}, summary = "달력 날짜 변경 조회(월 단위)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "메인 페이지 달력 조회 날짜 변경을 성공했습니다."),
            @ApiResponse(responseCode = "404", description = "존재하지 않는 사용자입니다.")
    })
    @GetMapping("/calendars")
    public Response<ResponseShowCalendarDto> showCalendar(@RequestParam(value = "year") Long year,
                                                          @RequestParam(value = "month") Long month,
                                                          @AuthenticationPrincipal UserDetailsImpl userDetails)
    {

        MemberDto memberDto = MemberDto.toMemberDto(userDetails);
        TodoDto todoDto = TodoDto.toTodoDto(memberDto, year, month);

        ResponseShowCalendarDto responseChangeCalendarDto = mainService.showCalendar(todoDto);

        return Response.success(200, "메인 페이지 달력 조회 날짜 변경을 성공했습니다.", responseChangeCalendarDto);
    }

    @Operation(tags = {"Main"}, summary = "데일리 로그 조회")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "데일리 로그 날짜 변경 조회를 성공했습니다."),
            @ApiResponse(responseCode = "404", description = "존재하지 않는 사용자입니다.")
    })

    @GetMapping("/dailys")
    public Response<ResponseShowDailyDto> showDaily(@RequestParam(value = "year") Long year,
                                                    @RequestParam(value = "month") Long month,
                                                    @RequestParam(value = "day") Long day,
                                                    @AuthenticationPrincipal UserDetailsImpl userDetails) {

        MemberDto memberDto = MemberDto.toMemberDto(userDetails);

        ResponseShowDailyDto responseShowDailyDto = mainService.showDaily(memberDto, year, month, day);

        return Response.success(200, "데일리 로그 날짜 변경 조회를 성공했습니다.", responseShowDailyDto);
    }
}
