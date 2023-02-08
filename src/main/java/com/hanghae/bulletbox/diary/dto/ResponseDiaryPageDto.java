package com.hanghae.bulletbox.diary.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Schema(description = "일기장 페이지 조회 응답 DTO")
public class ResponseDiaryPageDto {

    @Schema(description = "일기장 페이지 조회 응답 변수", type = "List")
    private List<MonthlyEmotionDto> emotions;

    @Schema(description = "일기장 서비스 범용 Dto")
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
