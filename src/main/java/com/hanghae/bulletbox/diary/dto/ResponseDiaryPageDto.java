package com.hanghae.bulletbox.diary.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ResponseDiaryPageDto {

    private List<MonthlyEmotionDto> emotions;

    private DiaryDto diary;

    @Builder
    private ResponseDiaryPageDto(List<MonthlyEmotionDto> emotions, DiaryDto diary) {
        this.emotions = emotions;
        this.diary = diary;
    }

    public static ResponseDiaryPageDto toResponseDiaryPageDto(List<MonthlyEmotionDto> emotions, DiaryDto diaryDto) {
        return ResponseDiaryPageDto.builder()
                .emotions(emotions)
                .diary(diaryDto)
                .build();
    }
}
