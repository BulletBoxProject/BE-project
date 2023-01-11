package com.hanghae.bulletbox.diary.dto;

import com.hanghae.bulletbox.category.dto.CategoryDto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ResponseMainDto {

    private List<CategoryDto> categories;
    private List<CalendarDto> calendar;
    private List<DailyDto> daily;

    public ResponseMainDto(List<CategoryDto> categories, List<CalendarDto> calendar, List<DailyDto> daily) {
        this.categories = categories;
        this.calendar = calendar;
        this.daily = daily;
    }

    public static ResponseMainDto toResponseMainDto(List<CategoryDto> categories, List<CalendarDto> calendar, List<DailyDto> daily) {
        return new ResponseMainDto(categories, calendar, daily);
    }
}
