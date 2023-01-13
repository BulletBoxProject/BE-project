package com.hanghae.bulletbox.diary.dto;

import com.hanghae.bulletbox.category.dto.CategoryDto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ResponseShowMainPageDto {

    private List<CategoryDto> categories;
    private List<CalendarDto> calendar;
    private List<DailyDto> daily;

    @Builder(access = AccessLevel.PROTECTED)
    private ResponseShowMainPageDto(List<CategoryDto> categories, List<CalendarDto> calendar, List<DailyDto> daily) {
        this.categories = categories;
        this.calendar = calendar;
        this.daily = daily;
    }

    public static ResponseShowMainPageDto toResponseShowMainPageDto(List<CategoryDto> categories, List<CalendarDto> calendar, List<DailyDto> daily) {
        return ResponseShowMainPageDto.builder()
                .categories(categories)
                .calendar(calendar)
                .daily(daily)
                .build();
    }
}
