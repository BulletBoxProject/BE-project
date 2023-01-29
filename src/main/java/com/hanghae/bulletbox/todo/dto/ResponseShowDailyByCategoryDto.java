package com.hanghae.bulletbox.todo.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Schema(description = "메인 페이지 카테고리별 데일리 로그 조회 응답 Dto")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ResponseShowDailyByCategoryDto {

    @Schema(description = "메인 페이지 데일리 로그 정보")
    private List<DailyDto> daily;

    private ResponseShowDailyByCategoryDto(List<DailyDto> dailyDtoList) {
        this.daily = dailyDtoList;
    }

    public static ResponseShowDailyByCategoryDto toResponseShowDailyByCategoryDto(List<DailyDto> dailyDtoList) {
        return new ResponseShowDailyByCategoryDto(dailyDtoList);
    }
}
