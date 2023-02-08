package com.hanghae.bulletbox.diary.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Schema(description = "일기장 달력 조회 응답 Dto")
public class ResponseDiaryCalendarPageDto {

    @Schema(description = "일기장 달력 조회 응답 변수", type = "List")
    private List<MonthlyEmotionDto> emotions;

    @Builder(access = AccessLevel.PRIVATE)
    private ResponseDiaryCalendarPageDto(List<MonthlyEmotionDto> emotions) {
        this.emotions = emotions;
    }

    public static ResponseDiaryCalendarPageDto toResponseDiaryCalendarPageDto(List<MonthlyEmotionDto> emotions){
        return ResponseDiaryCalendarPageDto.builder()
                .emotions(emotions)
                .build();
    }
}
