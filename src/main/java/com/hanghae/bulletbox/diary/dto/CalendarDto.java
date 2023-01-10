package com.hanghae.bulletbox.diary.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CalendarDto {

    private Long day;
    private Long count;

    public CalendarDto(Long day, Long count) {
        this.day = day;
        this.count = count;
    }

    public static CalendarDto toCalendar(Long day, Long count) {
        return new CalendarDto(day, count);
    }
}
