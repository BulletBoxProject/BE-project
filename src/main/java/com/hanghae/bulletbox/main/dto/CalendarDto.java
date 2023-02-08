package com.hanghae.bulletbox.main.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Schema(description = "달력 정보 Dto")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CalendarDto {

    @Schema(description = "할 일이 존재하는 요일", example = "28", type = "Long")
    private Long day;

    @Schema(description = "할 일이 존재하는 요일의 할 일 개수", example = "3", type = "Long")
    private Long count;

    @Builder
    private CalendarDto(Long day, Long count) {
        this.day = day;
        this.count = count;
    }

    public static CalendarDto toCalendar(Long day, Long count) {
        return CalendarDto.builder()
                .day(day)
                .count(count)
                .build();
    }
}
