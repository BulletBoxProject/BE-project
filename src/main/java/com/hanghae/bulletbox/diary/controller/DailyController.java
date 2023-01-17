package com.hanghae.bulletbox.diary.controller;

import com.hanghae.bulletbox.category.dto.ResponseCategoryDto;
import com.hanghae.bulletbox.common.response.Response;
import com.hanghae.bulletbox.common.security.UserDetailsImpl;
import com.hanghae.bulletbox.diary.dto.DailyTodoDto;
import com.hanghae.bulletbox.diary.dto.ResponseDailyDto;
import com.hanghae.bulletbox.diary.dto.ResponseShowDailyDto;
import com.hanghae.bulletbox.diary.dto.ResponseShowTodoCreatePageDto;
import com.hanghae.bulletbox.diary.service.DailyService;
import com.hanghae.bulletbox.diary.service.DailyTodoService;
import com.hanghae.bulletbox.member.entity.Member;
import com.hanghae.bulletbox.todo.dto.TodoDto;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import lombok.RequiredArgsConstructor;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import springfox.documentation.annotations.ApiIgnore;

@Tag(name = "Daily", description = "데일리 로그 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/dailys")
public class DailyController {

    private final DailyService dailyService;
    private final DailyTodoService dailyTodoService;

    @Operation(tags = {"Daily"}, summary = "데일리 로그 조회")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "데일리 페이지 조회를 성공했습니다."),
            @ApiResponse(code = 400, message = "존재하지 않는 사용자입니다.")
    })
    @GetMapping
    public Response<ResponseDailyDto> showDailyPage(@ApiIgnore @AuthenticationPrincipal UserDetailsImpl userDetails) {
        Long memberId = userDetails.getMember().getMemberId();
        ResponseDailyDto responseDailyDto = dailyService.showDailyPage(memberId);
        return Response.success(200, "데일리 페이지 조회를 성공했습니다.", responseDailyDto);
    }

    @Operation(tags = {"Daily"}, summary = "데일리 로그 조회 날짜 변경")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "데일리 로그 조회 날짜 변경을 성공했습니다."),
            @ApiResponse(code = 400, message = "존재하지 않는 사용자입니다.")
    })
    @GetMapping("/{todoYear}/{todoMonth}/{todoDay}")
    public Response<ResponseShowDailyDto> showDailyPageChangeDay(@PathVariable Long todoYear,
                                                                 @PathVariable Long todoMonth,
                                                                 @PathVariable Long todoDay,
                                                                 @ApiIgnore @AuthenticationPrincipal UserDetailsImpl userDetails) {
        Long memberId = userDetails.getMember().getMemberId();
        TodoDto todoDto = TodoDto.toTodoDto(memberId, todoYear, todoMonth, todoDay);
        ResponseShowDailyDto responseShowDailyDto = dailyService.showDailyPageChangeDay(todoDto);
        return Response.success(200, "데일리 로그 조회 날짜 변경을 성공했습니다.", responseShowDailyDto);
    }


    @Operation(tags = {"Daily"}, summary = "카테고리별 데일리 로그 조회")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "카테고리별 데일리 로그 조회를 성공했습니다."),
            @ApiResponse(code = 400, message = "존재하지 않는 사용자입니다.")
    })
    @GetMapping("/{todoYear}/{todoMonth}/{todoDay}/{categoryId}")
    public Response<ResponseCategoryDto> showDailyByCategory(@PathVariable Long todoYear,
                                                             @PathVariable Long todoMonth,
                                                             @PathVariable Long todoDay, @PathVariable Long categoryId,
                                                             @ApiIgnore @AuthenticationPrincipal UserDetailsImpl userDetails) {

        Long memberId = userDetails.getMember().getMemberId();
        TodoDto todoDto = TodoDto.toTodoDto(memberId, categoryId, todoYear, todoMonth, todoDay);
        ResponseCategoryDto responseCategoryDto = dailyService.showDailyByCategory(todoDto);
        return Response.success(200, "카테고리별 데일리 로그 조회를 성공했습니다.", responseCategoryDto);
    }

    // 할 일 추가 페이지 조회
    @GetMapping("/todo")
    public Response<ResponseShowTodoCreatePageDto> showTodoCreatePage(@RequestParam(value = "year") Long year,
                                                                      @RequestParam(value = "month") Long month,
                                                                      @RequestParam(value = "day") Long day,
                                                                      @AuthenticationPrincipal UserDetailsImpl userDetails) {

        Member member = userDetails.getMember();
        DailyTodoDto dailyTodoDto = DailyTodoDto.toDailyTodoDto(member, year, month, day);
        ResponseShowTodoCreatePageDto responseShowTodoCreatePageDto = dailyTodoService.showTodoCreatePage(dailyTodoDto);

        return Response.success(200, "할 일 추가 페이지 조회를 성공했습니다.", responseShowTodoCreatePageDto);
    }
}