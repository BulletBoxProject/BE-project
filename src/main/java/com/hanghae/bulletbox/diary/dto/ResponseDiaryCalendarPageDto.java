package com.hanghae.bulletbox.diary.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ResponseDiaryCalendarPageDto {

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
