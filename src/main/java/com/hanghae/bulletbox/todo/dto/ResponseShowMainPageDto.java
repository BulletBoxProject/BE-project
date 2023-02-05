package com.hanghae.bulletbox.todo.dto;

import com.hanghae.bulletbox.category.dto.CategoryDto;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Schema(description = "메인 페이지 응답 Dto")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ResponseShowMainPageDto {

    @Schema(description = "카테고리 정보 담는 변수", type = "List")
    private List<CategoryDto> categories;

    @Schema(description = "달력 정보 담는 변수", type = "List")
    private List<CalendarDto> calendar;

    @Schema(description = "할 일 정보 담는 변수", type = "List")
    private List<DailyDto> daily;

    @Schema(description = "첫 로그인 판별 여부", example = "true" , type = "String")
    private boolean firstLogin;

    @Builder(access = AccessLevel.PROTECTED)
    private ResponseShowMainPageDto(List<CategoryDto> categories, List<CalendarDto> calendar, List<DailyDto> daily, boolean firstLogin) {
        this.categories = categories;
        this.calendar = calendar;
        this.daily = daily;
        this.firstLogin = firstLogin;
    }

    public static ResponseShowMainPageDto toResponseShowMainPageDto(List<CategoryDto> categories, List<CalendarDto> calendar, List<DailyDto> daily, boolean firstLogin) {
        return ResponseShowMainPageDto.builder()
                .categories(categories)
                .calendar(calendar)
                .daily(daily)
                .firstLogin(firstLogin)
                .build();
    }
}
