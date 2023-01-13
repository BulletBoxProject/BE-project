package com.hanghae.bulletbox.diary.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ResponseShowCalendarDto {

    private List<CalendarDto> calendar;

    public ResponseShowCalendarDto(List<CalendarDto> calendarDtoList) {
        this.calendar = calendarDtoList;
    }

    public static ResponseShowCalendarDto toResponseChangeCalendarDto(List<CalendarDto> calendarDtoList) {
        return new ResponseShowCalendarDto(calendarDtoList);
    }
}
