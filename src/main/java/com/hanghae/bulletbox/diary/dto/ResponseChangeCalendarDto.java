package com.hanghae.bulletbox.diary.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ResponseChangeCalendarDto {

    private List<CalendarDto> calendar;

    public ResponseChangeCalendarDto(List<CalendarDto> calendarDtoList) {
        this.calendar = calendarDtoList;
    }

    public static ResponseChangeCalendarDto toResponseChangeCalendarDto(List<CalendarDto> calendarDtoList) {
        return new ResponseChangeCalendarDto(calendarDtoList);
    }
}
