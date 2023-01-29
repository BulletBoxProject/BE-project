package com.hanghae.bulletbox.todo.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Schema(description = "메인 페이지 달력 날짜 변경 조회 응답 Dto")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ResponseShowCalendarDto {

    @Schema(description = "매인 페이지 달력의 정보")
    private List<CalendarDto> calendar;

    @Builder(access = AccessLevel.PRIVATE)
    private ResponseShowCalendarDto(List<CalendarDto> calendarDtoList) {
        this.calendar = calendarDtoList;
    }

    public static ResponseShowCalendarDto toResponseChangeCalendarDto(List<CalendarDto> calendarDtoList) {
        return ResponseShowCalendarDto.builder()
                .calendarDtoList(calendarDtoList)
                .build();
    }
}
